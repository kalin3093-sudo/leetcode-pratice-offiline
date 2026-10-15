#!/usr/bin/env node
// Batch-verify every problems/<slug>/Marker.java through the exact markerRunner judge path:
// isCorrect(javaGetFullParam(tc), to_<outputType>(example.output)) must print true for every pair.
// One Docker container; compile+run per dir with xargs -P4. Report: %TEMP%/lc-verify-report.json
import fs from 'fs'
import path from 'path'
import os from 'os'
import vm from 'node:vm'
import { execFileSync, spawnSync } from 'child_process'
import Dockerode from 'dockerode'
import {
  javaHelperMethods, javaGetFullParam, formatAndSplitJavaString, getDisplayFuncName,
  javaListNodeClass, javaTreeNodeClass, javaGraphNodeClass, javaImage
} from '../src/lib/utils/javaUtil.ts'

const PROBLEMS = path.resolve('problems')
function fail(msg) { console.error(msg); process.exit(1) }
// --only: don't clobber the full-round report or stage dir; unique per process for concurrent runs
const onlyIdx = process.argv.indexOf('--only')
if (onlyIdx >= 0 && !process.argv[onlyIdx + 1]) fail('--only requires a comma-separated slug list')
const ONLY = onlyIdx < 0 ? null : new Set(process.argv[onlyIdx + 1].split(',').filter(Boolean))
const STAGE = path.join(os.tmpdir(), ONLY ? `lc-verify-${process.pid}` : 'lc-verify')
// --report <path> > pid-unique (with --only) > default (never clobber the full-round report by accident)
const repIdx = process.argv.indexOf('--report')
if (repIdx >= 0 && !process.argv[repIdx + 1]) fail('--report requires a file path')
const REPORT = repIdx >= 0 && process.argv[repIdx + 1]
  ? path.resolve(process.argv[repIdx + 1])
  : ONLY ? path.join(os.tmpdir(), `lc-verify-one-${process.pid}.json`) : path.join(os.tmpdir(), 'lc-verify-report.json')
// --tests official: pair source = official-tests.json, line 1 = reference self-check
// (any other value = typo'd gate command — refuse rather than silently run examples mode)
const testsIdx = process.argv.indexOf('--tests')
if (testsIdx >= 0 && process.argv[testsIdx + 1] !== 'official') fail('--tests accepts only "official"')
const OFFICIAL = testsIdx >= 0
const HOST = process.env.VERIFY_HOST === '1'
const JH = process.env.JDK_HOME || 'H:/javajdk/bin'

// mirror src/routes/api/submit/+server.ts:85-107 — large-test values may be `@javascript:<expr>`
const JAVASCRIPT_PREFIX = '@javascript:'
function expandJs(tc) {
  for (const k of Object.keys(tc)) {
    const v = tc[k]
    if (typeof v === 'string' && v.startsWith(JAVASCRIPT_PREFIX)) {
      try {
        const evaluated = vm.runInNewContext(v.slice(JAVASCRIPT_PREFIX.length), {}, { timeout: 2000 })
        tc[k] = typeof evaluated === 'string' ? evaluated
          : typeof evaluated === 'object' ? JSON.stringify(evaluated)
          : String(evaluated)
      } catch {
        tc[k] = v.replace(JAVASCRIPT_PREFIX, '')
      }
    }
  }
  return tc
}

const VRUN = `#!/bin/sh
cd /app/p || exit 1
: > ../results.txt
ls -d */ | xargs -P 4 -I{} sh -c '
  d="\${1%/}"
  cd "$d" || exit
  if javac *.java >compile.log 2>&1; then
    if timeout 15 java Main >run.log 2>&1; then st=RUN_OK; else st=RUN_FAIL; fi
  else
    st=COMPILE_FAIL
  fi
  printf "%s\\t%s\\n" "$d" "$st" >> ../../results.txt
' sh {}
`

// mirror generate-leetcode.mjs parseTestValue: long/[ → raw, else JSON.parse, fallback strip quotes
function coerceVal(raw, lcType) {
  const t = raw.trim()
  if (lcType === 'long') return raw
  if (t.startsWith('[')) return raw
  try {
    return JSON.parse(t)
  } catch {
    return t.startsWith('"') && t.endsWith('"') ? t.slice(1, -1) : t
  }
}

// statement example input -> tc object, e.g. "nums = [2, 7], target = 9"
// (testCases[] is NOT index-aligned with examples[] — derive tc from the example itself)
function parseExampleTc(inputRaw, params) {
  if (!params?.length) return null
  let input = inputRaw.replace(/\s+/g, ' ').trim()  // multi-line inputs (ops/values style) break Java literals
  for (const p of params) {
    input = input.replace(new RegExp(`(^|,)\\s*${p.name}\\s*=\\s*`), (m, g1) => (g1 ? ', ' : '') + `${p.name} = `)
  }
  const tc = {}
  let cursor = 0
  for (let i = 0; i < params.length; i++) {
    const p = params[i]
    const key = `${p.name} = `
    let idx = input.indexOf(key, cursor)
    if (idx < 0) idx = input.indexOf(key)
    if (idx < 0) return parseFallback(input, params)
    const vs = idx + key.length
    const nextKey = i < params.length - 1 ? `, ${params[i + 1].name} = ` : null
    let ve = nextKey ? input.indexOf(nextKey, vs) : input.length
    if (ve < 0) ve = input.length
    tc[p.name] = coerceVal(input.slice(vs, ve), p.type)
    cursor = ve
  }
  return tc
}

// fallback: bare single value, or positional `token = value` list (param-name drift e.g. edgeList vs edges)
function parseFallback(input, params) {
  if (params.length === 1) return { [params[0].name]: coerceVal(input, params[0].type) }
  const toks = [...input.matchAll(/(\w+)\s*=\s*/g)]
  if (toks.length !== params.length) return null
  const tc = {}
  for (let i = 0; i < params.length; i++) {
    const start = toks[i].index + toks[i][0].length
    const end = i + 1 < toks.length ? toks[i + 1].index : input.length
    tc[params[i].name] = coerceVal(input.slice(start, end).replace(/,\s*$/, ''), params[i].type)
  }
  return tc
}

function cleanOut(out, outputType) {
  let t = String(out ?? '').trim()
  // statements quote scalars ("c", "aaab") but the judge's user display is bare
  if (outputType === 'string' || outputType === 'char') {
    if (t.startsWith('"') && t.endsWith('"') && t.length >= 2) {
      try { return JSON.parse(t) } catch { t = t.slice(1, -1) }
    }
    // trailing annotations: `"wertf" (one valid order)`
    const ann = t.match(/^"([^"]*)"\s*\([^)]*\)$/)
    if (ann) return ann[1]
  }
  return t
}

function buildRunner(meta, pairs, official = false) {
  const testCalls = pairs
    .map(({ tc, out }) => {
      const fullParam = javaGetFullParam(meta.params, tc)
      const displayFunc = getDisplayFuncName(meta.outputType)
      // official: reference self-check — checker must accept the reference's own output
      const check = official
        ? `System.out.println(new Marker().isCorrect(${fullParam}, new Marker().${meta.functionName}(${fullParam})));`
        : `System.out.println(new Marker().isCorrect(${fullParam}, to_${meta.outputType}(${formatAndSplitJavaString(String(out ?? ''))})));`
      return `try {
            ${check}
        } catch (Exception __e) {
            System.out.println("false");
        }
        System.out.println(${displayFunc}(new Marker().${meta.functionName}(${fullParam})));
        System.out.println("---");`
    })
    .join('\n        ')
  return `import java.util.*;
public class Main {
        ${javaHelperMethods}
        public static void main(String[] args) throws Exception {
            ${testCalls}
        }
    }`
}

// ---------- stage ----------
fs.rmSync(STAGE, { recursive: true, force: true })
fs.mkdirSync(path.join(STAGE, 'p'), { recursive: true })
fs.writeFileSync(path.join(STAGE, 'vrun.sh'), VRUN)

const staged = []
const noPairs = []
const parseErrors = []
const stagedInfo = new Map()  // slug -> {pairs, outputType} for post-run refWarn
for (const d of fs.readdirSync(PROBLEMS)) {
  if (ONLY && !ONLY.has(d)) continue
  const dir = path.join(PROBLEMS, d)
  if (!fs.existsSync(path.join(dir, 'Marker.java'))) continue
  let meta
  try {
    meta = JSON.parse(fs.readFileSync(path.join(dir, 'metadata.json'), 'utf-8'))
  } catch (e) {
    parseErrors.push(`${d}/metadata.json: ${e.message}`)
    continue
  }
  const pairs = []
  if (OFFICIAL) {
    // direct tc objects from official-tests.json, no example parsing; out: null (self-check mode)
    const of = path.join(dir, 'official-tests.json')
    if (fs.existsSync(of)) {
      try {
        const arr = JSON.parse(fs.readFileSync(of, 'utf-8'))
        for (const tc of Array.isArray(arr) ? arr : []) {
          if (tc && typeof tc === 'object') pairs.push({ tc: expandJs(tc), out: null })
        }
      } catch (e) {
        parseErrors.push(`${d}/official-tests.json: ${e.message}`)
        continue
      }
    }
  } else {
    for (const ex of meta.examples ?? []) {
      const tc = parseExampleTc(String(ex.input ?? ''), meta.params)
      if (!tc) continue
      pairs.push({ tc, out: cleanOut(ex.output, meta.outputType) })
    }
  }
  if (!pairs.length) { noPairs.push(d); continue }
  const target = path.join(STAGE, 'p', d)
  fs.mkdirSync(target, { recursive: true })
  fs.writeFileSync(path.join(target, 'ListNode.java'), javaListNodeClass)
  fs.writeFileSync(path.join(target, 'TreeNode.java'), javaTreeNodeClass)
  fs.writeFileSync(path.join(target, 'GraphNode.java'), javaGraphNodeClass)
  fs.writeFileSync(path.join(target, 'Marker.java'), fs.readFileSync(path.join(dir, 'Marker.java'), 'utf-8'))
  fs.writeFileSync(path.join(target, 'Main.java'), buildRunner(meta, pairs, OFFICIAL))
  fs.writeFileSync(path.join(target, 'pairs.json'), JSON.stringify(pairs))
  staged.push(d)
  stagedInfo.set(d, { pairs, outputType: meta.outputType })
}
// C1 guard: every --only slug must actually stage — a typo'd batch file/path must not
// produce a vacuous green report (total: 0, wrongCount: 0, exit 0)
const unstaged = ONLY ? [...ONLY].filter((s) => !staged.includes(s)) : []
console.log(`staged: ${staged.length}, no-pairs: ${noPairs.length}`)
if (unstaged.length) console.log('unstaged:', JSON.stringify(unstaged))
if (noPairs.length) console.log('no-pairs:', JSON.stringify(noPairs))
if (parseErrors.length) console.log('parse-errors:', JSON.stringify(parseErrors))
if (process.argv.includes('--dry')) process.exit(unstaged.length || parseErrors.length ? 1 : 0)

let results
let fullDir

if (HOST) {
  // fast local loop with the host JDK — same javac/java steps as the container
  results = {}
  for (const s of staged) {
    const wd = path.join(STAGE, 'p', s)
    const files = fs.readdirSync(wd).filter(f => f.endsWith('.java'))
    const c = spawnSync(path.join(JH, 'javac.exe'), files, { cwd: wd, encoding: 'utf8', timeout: 60000 })
    if (c.status !== 0) {
      fs.writeFileSync(path.join(wd, 'compile.log'), (c.stdout || '') + (c.stderr || ''))
      results[s] = 'COMPILE_FAIL'
      continue
    }
    const r = spawnSync(path.join(JH, 'java.exe'), ['Main'], { cwd: wd, encoding: 'utf8', timeout: 15000, maxBuffer: 32 * 1024 * 1024 })
    fs.writeFileSync(path.join(wd, 'run.log'), (r.stdout || '') + (r.stderr || '') + (r.signal === 'SIGTERM' ? '[timeout]\n' : ''))
    results[s] = r.status === 0 ? 'RUN_OK' : 'RUN_FAIL'
  }
  fullDir = STAGE
  console.log(`host results: ${Object.keys(results).length}/${staged.length}`)
} else {

const tarPath = path.join(os.tmpdir(), 'lc-verify.tar')
execFileSync('tar', ['-cf', tarPath, '-C', STAGE, '.'], { stdio: 'inherit' })

// ---------- docker ----------
const docker = new Dockerode()
execFileSync('docker', ['pull', javaImage], { stdio: 'inherit' })
const container = await docker.createContainer({ Image: javaImage, Cmd: ['sh', '-c', 'sleep 7200'], WorkingDir: '/app', Tty: false })
await container.start()
console.log('container:', container.id.slice(0, 12))

// upload tar (prebuilt tar buffer accepted directly by putArchive)
const tarBuf = fs.readFileSync(tarPath)
await new Promise((res, rej) => {
  container.putArchive(tarBuf, { path: '/app' }, (e, r) => (e ? rej(e) : res(r)))
})
console.log('uploaded, compiling+running...')
const vExec = await container.exec({ Cmd: ['sh', '-c', 'sh /app/vrun.sh'], AttachStdout: false, AttachStderr: false })
vExec.start({ hijack: false, stdin: false }).then((s) => { try { s.resume() } catch {} }).catch(() => {})

async function countResults() {
  // dockerode's exec stream proved unreliable here; the CLI is not
  try {
    const buf = execFileSync('docker', ['exec', container.id, 'sh', '-c', 'wc -l < /app/results.txt 2>/dev/null || echo 0'], { encoding: 'utf8' })
    return parseInt(buf.trim()) || 0
  } catch { return -1 }
}

const deadline = Date.now() + 3_600_000
let n = 0
let lastPrint = 0
while (Date.now() < deadline) {
  n = await countResults()
  if (n >= staged.length) break
  if (Date.now() - lastPrint > 20_000) { console.log(`progress: ${n}/${staged.length}`); lastPrint = Date.now() }
  await new Promise((r) => setTimeout(r, 10_000))
}
console.log(`results: ${n}/${staged.length}`)
if (n < staged.length) {
  console.error(`incomplete — container kept for salvage: ${container.id}`)
  process.exit(1)
}

// one pull for results + all logs, parse locally
const arch = await container.getArchive({ path: '/app/results.txt' })
const chunks = []
await new Promise((res) => arch.on('data', (c) => chunks.push(c)).on('end', res))
fs.writeFileSync(path.join(os.tmpdir(), 'lc-results.tar'), Buffer.concat(chunks))
execFileSync('tar', ['-xf', path.join(os.tmpdir(), 'lc-results.tar'), '-C', os.tmpdir()], { stdio: 'ignore' })

results = {}
for (const line of fs.readFileSync(path.join(os.tmpdir(), 'results.txt'), 'utf-8').split('\n')) {
  if (!line.trim()) continue
  const [slug, st] = line.split('\t')
  results[slug] = st
}

console.log('pulling logs...')
const archP = await container.getArchive({ path: '/app/p' })
const chunksP = []
await new Promise((res) => archP.on('data', (c) => chunksP.push(c)).on('end', res))
const fullTar = path.join(os.tmpdir(), 'lc-full.tar')
fs.writeFileSync(fullTar, Buffer.concat(chunksP))
fullDir = path.join(os.tmpdir(), 'lc-full')
fs.rmSync(fullDir, { recursive: true, force: true })
fs.mkdirSync(fullDir, { recursive: true })
execFileSync('tar', ['-xf', fullTar, '-C', fullDir], { stdio: 'ignore' })
await container.remove({ force: true })

} // end !HOST

const detail = {}
for (const [s, st] of Object.entries(results)) {
  if (st === 'RUN_OK') continue
  const clog = path.join(fullDir, 'p', s, 'compile.log')
  const rlog = path.join(fullDir, 'p', s, 'run.log')
  detail[s] = {
    compile: fs.existsSync(clog) ? fs.readFileSync(clog, 'utf-8').slice(0, 1500) : '',
    run: fs.existsSync(rlog) ? fs.readFileSync(rlog, 'utf-8').slice(0, 1500) : ''
  }
}

const wrong = []
for (const [s, st] of Object.entries(results)) {
  if (st !== 'RUN_OK') continue
  const rlog = path.join(fullDir, 'p', s, 'run.log')
  const log = fs.existsSync(rlog) ? fs.readFileSync(rlog, 'utf-8') : ''
  const blocks = log.split('---').map((b) => b.trim()).filter(Boolean)
  const firsts = blocks.map((b) => b.split('\n')[0].trim())
  if (!firsts.length) wrong.push({ slug: s, reason: 'empty-output', log: log.slice(0, 600) })
  else if (firsts.some((f) => f !== 'true')) wrong.push({ slug: s, reason: 'isCorrect-false', log: log.slice(0, 600) })
}

// examples mode only: reference display vs statement expected — catches wrong reference
// methods that the property checker would mask. Non-fatal; multi-answer order diffs expected.
const refWarn = []
if (!OFFICIAL) {
  // strip quotes too: statements quote list elements (["0->2"]) while the judge's
  // display is bare ([0->2]) — format artifact, not a wrong-reference signal
  const norm = (s) => String(s ?? '').replace(/[\s"]/g, '')
  for (const [s, st] of Object.entries(results)) {
    if (st !== 'RUN_OK') continue
    const info = stagedInfo.get(s)
    if (!info) continue
    const rlog = path.join(fullDir, 'p', s, 'run.log')
    const log = fs.existsSync(rlog) ? fs.readFileSync(rlog, 'utf-8') : ''
    const blocks = log.split('---').map((b) => b.trim()).filter(Boolean)
    const n = Math.min(blocks.length, info.pairs.length)
    for (let i = 0; i < n; i++) {
      const exp = info.pairs[i].out
      if (exp === null || exp === undefined) continue
      const lines = blocks[i].split('\n')
      if (lines.length < 2) continue  // no display line (crash mid-block) — wrong-detection already covers this
      const ref = cleanOut(lines.slice(1).join('\n'), info.outputType)
      if (norm(ref) !== norm(exp)) refWarn.push({ slug: s, expected: exp, ref })
    }
  }
}

const report = {
  mode: OFFICIAL ? 'official' : 'examples',
  total: staged.length,
  runOk: Object.values(results).filter((v) => v === 'RUN_OK').length,
  compileFail: Object.values(results).filter((v) => v === 'COMPILE_FAIL').length,
  runFail: Object.values(results).filter((v) => v === 'RUN_FAIL').length,
  missing: staged.filter((s) => !results[s]),
  wrongCount: wrong.length,
  wrong,
  unstaged,
  noPairs,
  parseErrors,
  refWarnCount: refWarn.length,
  refWarn,
  detail
}
fs.writeFileSync(REPORT, JSON.stringify(report, null, 2))
console.log(JSON.stringify({ ...report, wrong: report.wrong.slice(0, 10), refWarn: report.refWarn.slice(0, 10), detail: undefined, reportFile: REPORT }, null, 1))
// gates must never pass vacuously: any wrong/compile/run/missing/unstaged/parse failure = exit 1
process.exit(
  report.wrongCount || report.compileFail || report.runFail || report.missing.length
    || unstaged.length || parseErrors.length ? 1 : 0
)
