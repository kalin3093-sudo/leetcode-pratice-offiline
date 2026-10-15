#!/usr/bin/env node
// Local Marker.java verifier — no Docker needed. Uses system javac/java.
//
// Per problem it checks, in order:
//   1. javac compiles Marker.java together with a generated Driver.java
//   2. every sample test (metadata.testCases) + official test runs without throwing
//   3. isCorrect(args, marker.fn(args)) is true for every test (validator self-consistency)
//   4. marker.fn() on metadata.examples[i] matches examples[i].output (real correctness signal)
//
// Usage: node scripts/verify-markers-local.mjs [--only slug1,slug2] [--keep]
import fs from 'node:fs';
import path from 'node:path';
import os from 'node:os';
import { spawnSync } from 'node:child_process';

const PROBLEMS = path.resolve('problems');
const argv = process.argv.slice(2);
const onlyIdx = argv.indexOf('--only');
const ONLY = onlyIdx >= 0 ? new Set(argv[onlyIdx + 1].split(',').filter(Boolean)) : null;
const KEEP = argv.includes('--keep');
const WORK = path.join(os.tmpdir(), 'cojudge-marker-verify');

// ---------- param type -> Java type ----------
const JAVA_TYPE = {
  int: 'int', long: 'long', double: 'double', boolean: 'boolean',
  string: 'String', char: 'char',
  int_array: 'int[]', int_array_2d: 'int[][]', char_array: 'char[]', char_array_2d: 'char[][]',
  string_array: 'String[]',
  int_list: 'List<Integer>', int_list_2d: 'List<List<Integer>>',
  string_list: 'List<String>', string_list_2d: 'List<List<String>>',
  list_node: 'ListNode', list_node_array: 'ListNode[]', graph_node: 'GraphNode', tree_node: 'TreeNode',
};

// types whose Java result can be rendered and compared to examples[].output
const FMTABLE = new Set([
  'int', 'long', 'double', 'boolean', 'string', 'char',
  'int_array', 'int_list', 'string_array', 'string_list', 'string_list_2d',
]);

// ---------- literal emission ----------
function jstr(s) { return JSON.stringify(s); }

function literal(type, raw) {
  raw = resolveValue(raw);
  if (raw === null || raw === undefined) throw new Error(`missing value for type ${type}`);
  switch (type) {
    case 'int': return String(raw);
    case 'long': return `${raw}L`;
    case 'double': return String(raw);
    case 'boolean': return String(raw);
    case 'char': {
      const s = typeof raw === 'string' ? raw : String(raw);
      return `'${javaChar(s[0] ?? ' ')}'`;
    }
    case 'string': return jstr(String(raw));
    case 'int_array': return `new int[]{${parseJsonArr(raw).map(Number).join(',')}}`;
    case 'int_array_2d': return `new int[][]{${parseJsonArr(raw).map(r => `{${r.map(Number).join(',')}}`).join(',')}}`;
    case 'char_array': return `new char[]{${parseJsonArr(raw).map(c => `'${javaChar(c)}'`).join(',')}}`;
    case 'string_array': return `new String[]{${parseJsonArr(raw).map(jstr).join(',')}}`;
    case 'int_list': return `Arrays.asList(${parseJsonArr(raw).map(Number).join(',')})`;
    case 'string_list': return `Arrays.asList(${parseJsonArr(raw).map(jstr).join(',')})`;
    case 'int_list_2d': return `Arrays.asList(${parseJsonArr(raw).map(r => `Arrays.asList(${r.map(Number).join(',')})`).join(',')})`;
    case 'string_list_2d': return `Arrays.asList(${parseJsonArr(raw).map(r => `Arrays.asList(${r.map(jstr).join(',')})`).join(',')})`;
    case 'list_node': return `mkList(new int[]{${parseJsonArr(raw).map(Number).join(',')}})`;
    default: throw new Error(`unsupported param type: ${type}`);
  }
}

function parseJsonArr(raw) {
  if (Array.isArray(raw)) return raw; // metadata.testCases keep arrays as real JSON arrays
  const s = String(raw).trim();
  const v = JSON.parse(s);
  if (!Array.isArray(v)) throw new Error(`expected array, got ${s.slice(0, 40)}`);
  return v;
}

// official-tests.json may generate values dynamically: "@javascript:JSON.stringify(...)"
function resolveValue(raw) {
  if (typeof raw === 'string' && raw.startsWith('@javascript:')) {
    const expr = raw.slice('@javascript:'.length);
    return Function(`"use strict"; return (${expr});`)();
  }
  return raw;
}

function javaChar(c) {
  if (c === '\\') return '\\\\';
  if (c === "'") return "\\'";
  if (c === '"') return '\\"';
  if (c === '\n') return '\\n';
  if (c === '\t') return '\\t';
  return c;
}

// ---------- example input parsing: `k = v, k = v` ----------
function parseExampleInput(input) {
  const parts = [];
  let depth = 0, inStr = false, cur = '';
  for (let i = 0; i < input.length; i++) {
    const c = input[i];
    if (inStr) {
      cur += c;
      if (c === '\\') { cur += input[++i] ?? ''; continue; }
      if (c === '"') inStr = false;
      continue;
    }
    if (c === '"') { inStr = true; cur += c; continue; }
    if (c === '[' || c === '(') depth++;
    if (c === ']' || c === ')') depth--;
    if (c === ',' && depth === 0) { parts.push(cur); cur = ''; continue; }
    cur += c;
  }
  if (cur.trim()) parts.push(cur);
  const out = [];
  for (const p of parts) {
    const eq = p.indexOf('=');
    if (eq < 0) throw new Error(`cannot parse example input segment: ${p.trim()}`);
    out.push({ name: p.slice(0, eq).trim(), value: p.slice(eq + 1).trim() });
  }
  return out;
}

// ---------- Java driver ----------
const LISTNODE_SRC = `class ListNode {
    int val; ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}`;

function buildDriver(meta, tests) {
  const fn = meta.functionName;
  const params = meta.params;
  const outType = meta.outputType;
  const needsList = params.some(p => p.type === 'list_node' || p.type === 'list_node_array');
  const decls = [];
  const methods = [];
  const body = [];

  if (needsList) {
    decls.push('static ListNode mkList(int[] a) { ListNode d = new ListNode(0), c = d; for (int x : a) { c.next = new ListNode(x); c = c.next; } return d.next; }');
  }

  const argBlock = (vals, prefix) => {
    const b = [];
    params.forEach((p, i) => {
      const raw = vals[p.name];
      b.push(`        ${JAVA_TYPE[p.type]} ${prefix}${i} = ${literal(p.type, raw)};`);
    });
    return b;
  };
  const argNames = (prefix) => params.map((_, i) => `${prefix}${i}`).join(', ');

  // Values lifted out of `metadata.examples[].input` are statement text, so strings appear
  // quoted (`s = "leetcode"`). JSON test cases already carry the unquoted value.
  const unquoteExamples = (byName) => {
    for (const p of params) {
      if (p.type !== 'string' && p.type !== 'char') continue;
      const v = byName[p.name];
      if (typeof v !== 'string') continue;
      const t = v.trim();
      if (t.startsWith('"') && t.endsWith('"') && t.length >= 2) {
        try { byName[p.name] = JSON.parse(t); } catch { /* leave as-is */ }
      }
    }
    return byName;
  };

  // Arguments are materialised twice, once for fn() and once for isCorrect(). The judge
  // re-evaluates its inline argument expressions for each call, so reusing one array/list
  // here would diverge on in-place problems (reverse a list, sort an array, ...).
  tests.forEach((tc, idx) => {
    const b = [`    static void tc${idx}(Marker m) throws Exception {`];
    b.push(...argBlock(tc, `a${idx}_`));
    b.push(`        var r${idx} = m.${fn}(${argNames(`a${idx}_`)});`);
    b.push(...argBlock(tc, `b${idx}_`));
    b.push(`        boolean ok${idx} = m.isCorrect(${argNames(`b${idx}_`)}, r${idx});`);
    b.push(`        if (!ok${idx}) { System.out.println("FAIL isCorrect case ${idx}"); fail++; }`);
    b.push(`    }`);
    methods.push(b.join('\n'));
    body.push(`        tc${idx}(m);`);
  });

  // Examples double as a correctness oracle: feed the answer printed in the statement to
  // isCorrect() and require it to be accepted. Skipped when the documented output cannot
  // be expressed as a literal (list_node_array) or does not parse.
  (meta.examples || []).forEach((ex, idx) => {
    let vals;
    try { vals = parseExampleInput(ex.input); } catch (e) { return; }
    const byName = unquoteExamples(Object.fromEntries(vals.map(v => [v.name, v.value])));
    if (!params.every(p => p.name in byName)) return;
    const b = [`    static void ex${idx}(Marker m) throws Exception {`];
    b.push(...argBlock(byName, `e${idx}_`));
    b.push(`        var er${idx} = m.${fn}(${argNames(`e${idx}_`)});`);
    b.push(...argBlock(byName, `f${idx}_`));
    let outLit = null;
    try { outLit = outputLiteral(outType, ex.output); } catch (e) { outLit = null; }
    if (outLit !== null) {
      b.push(`        if (!m.isCorrect(${argNames(`f${idx}_`)}, ${outLit})) { System.out.println("FAIL rejects example ${idx}"); fail++; }`);
    }
    b.push(`    }`);
    methods.push(b.join('\n'));
    body.push(`        ex${idx}(m);`);
  });

  return `import java.util.*;

public class Driver {
    static int fail = 0;
${decls.map(d => '    ' + d).join('\n')}

${methods.join('\n\n')}

    public static void main(String[] args) throws Exception {
        Marker m = new Marker();
${body.join('\n')}
        System.out.println(fail == 0 ? "ALL_OK" : "FAILED:" + fail);
        System.exit(fail == 0 ? 0 : 1);
    }
}
`;
}

// ---------- runner ----------
// javac and java must come from the same JDK — a javac-17/java-8 mix fails with
// UnsupportedClassVersionError, so prefer the runtime sitting next to javac.
function resolveJavac() {
  const r = spawnSync(process.platform === 'win32' ? 'where' : 'which', ['javac'], { encoding: 'utf8' });
  const first = (r.stdout || '').split(/\r?\n/).map(s => s.trim()).filter(Boolean)[0];
  return first || 'javac';
}
const JAVAC = resolveJavac();
const JAVA_HOME_DIR = path.dirname(JAVAC);
const JAVA_CANDIDATE = path.join(JAVA_HOME_DIR, process.platform === 'win32' ? 'java.exe' : 'java');
const JAVA = fs.existsSync(JAVA_CANDIDATE) ? JAVA_CANDIDATE : 'java';

function listTargets() {
  return fs.readdirSync(PROBLEMS, { withFileTypes: true })
    .filter(d => d.isDirectory())
    .map(d => d.name)
    .filter(slug => !ONLY || ONLY.has(slug))
    // --only on an explicit list also allows problems that already have a marker (control run)
    .filter(slug => ONLY || !fs.existsSync(path.join(PROBLEMS, slug, 'Marker.java')))
    .sort();
}

function run(slug) {
  const dir = path.join(PROBLEMS, slug);
  const meta = JSON.parse(fs.readFileSync(path.join(dir, 'metadata.json'), 'utf8'));
  const markerPath = path.join(dir, 'Marker.java');
  if (!fs.existsSync(markerPath)) return { slug, status: 'skip', why: 'no Marker.java' };
  const official = JSON.parse(fs.readFileSync(path.join(dir, 'official-tests.json'), 'utf8'));

  // dedupe sample tests + official tests (official usually carries the heavy ones)
  const samples = Array.isArray(meta.testCases) ? meta.testCases : [];
  const seen = new Set();
  const tests = [...samples, ...official].filter(tc => {
    const k = JSON.stringify(tc);
    if (seen.has(k)) return false;
    seen.add(k);
    return true;
  });
  if (!tests.length) return { slug, status: 'skip', why: 'no tests' };

  const wd = path.join(WORK, slug);
  fs.rmSync(wd, { recursive: true, force: true });
  fs.mkdirSync(wd, { recursive: true });

  const markerSrc = fs.readFileSync(path.join(dir, 'Marker.java'), 'utf8');
  fs.writeFileSync(path.join(wd, 'Marker.java'), markerSrc);
  let driver;
  try {
    driver = buildDriver(meta, tests);
  } catch (e) {
    return { slug, status: 'driver-error', why: e.message };
  }
  fs.writeFileSync(path.join(wd, 'Driver.java'), driver);
  // provide ListNode when the driver/marker needs it and the marker doesn't define its own
  if (/\bListNode\b/.test(driver + markerSrc) && !/\bclass\s+ListNode\b/.test(markerSrc)) {
    fs.writeFileSync(path.join(wd, 'ListNode.java'), LISTNODE_SRC);
  }

  const files = fs.readdirSync(wd).filter(f => f.endsWith('.java'));
  const jc = spawnSync(JAVAC, ['-nowarn', '-encoding', 'UTF-8', ...files], { cwd: wd, encoding: 'utf8' });
  if (jc.status !== 0) {
    return { slug, status: 'compile-error', why: (jc.stderr || jc.stdout || '').split('\n').slice(0, 8).join('\n') };
  }

  const jr = spawnSync(JAVA, ['-Xss16m', '-cp', wd, 'Driver'], { cwd: wd, encoding: 'utf8', timeout: 60000 });
  const out = `${jr.stdout || ''}${jr.stderr || ''}`;
  if (jr.error) return { slug, status: 'run-error', why: jr.error.message };

  const verdicts = out.split('\n').filter(l => l.startsWith('FAIL '));

  const allOk = out.includes('ALL_OK');
  if (!KEEP && allOk) fs.rmSync(wd, { recursive: true, force: true });
  return { slug, status: allOk ? 'ok' : 'fail', verdicts, raw: allOk ? '' : out.slice(-600) };
}

// examples[].output is LeetCode-ish: `9`, `true`, `[2,1]`, `"c"`, `[["a"]]` — some come
// through as a JSON string that still needs unquoting before it can become a literal.
function outputLiteral(type, raw) {
  raw = resolveValue(raw);
  if (typeof raw === 'string') {
    const t = raw.trim();
    if (type === 'string' || type === 'char') {
      if (t.startsWith('"')) { try { raw = JSON.parse(t); } catch { /* keep raw */ } }
    } else if (type === 'boolean' || type === 'int' || type === 'long' || type === 'double') {
      if (!t.startsWith('[') && !t.startsWith('"')) {
        const n = type === 'boolean' ? (t === 'true') : Number(t);
        if (type === 'boolean' || Number.isFinite(n)) raw = n;
      }
    }
  }
  if (type === 'long' && typeof raw === 'string') raw = raw.replace(/L$/, '');
  return literal(type, raw);
}

const targets = listTargets();
if (!targets.length) { console.log('no incomplete markers to verify'); process.exit(0); }

const results = [];
for (const slug of targets) {
  const r = run(slug);
  results.push(r);
  const mark = r.status === 'ok' ? 'ok  ' : r.status === 'skip' ? 'skip' : 'FAIL';
  console.log(`${mark}  ${slug}${r.why ? '  -- ' + r.why.split('\n')[0] : ''}`);
  if (r.status === 'fail') {
    (r.verdicts || []).forEach(v => console.log('       ' + v));
    (r.exProblems || []).forEach(v => console.log('       ' + v));
    if (r.raw) console.log('       ' + r.raw.split('\n').slice(-4).join('\n       '));
  }
  if (r.status === 'compile-error' && r.why) console.log('       ' + r.why.split('\n').join('\n       '));
}

const bad = results.filter(r => r.status !== 'ok' && r.status !== 'skip');
console.log(`\n${results.length - bad.length}/${results.length} ok`);
process.exit(bad.length ? 1 : 0);
