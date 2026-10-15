// Phase 2: generate problems/ dirs + courses/leetcode/courseinfo.json from %TEMP%\lc-cache
// Usage: node scripts/generate-leetcode.mjs [--limit N] [--only slug] [--dry]
import fs from 'node:fs'
import path from 'node:path'
import os from 'node:os'
import TurndownService from 'turndown'
import { gfm } from 'turndown-plugin-gfm'

const REPO = process.cwd()
const CACHE = path.join(os.tmpdir(), 'lc-cache')
const PROBLEMS = path.join(REPO, 'problems')
const COURSE_DIR = path.join(REPO, 'courses', 'leetcode')

const args = process.argv.slice(2)
const limit = args.includes('--limit') ? parseInt(args[args.indexOf('--limit') + 1], 10) : Infinity
const only = args.includes('--only') ? args[args.indexOf('--only') + 1] : null
const dry = args.includes('--dry')

// LC type -> cojudge type. null = known but unsupported (skip). double is return-only
// (python param fallback passes it as a string literal, 3 problems affected).
const BASE_MAP = {
  integer: 'int',
  long: 'long',
  character: 'char',
  string: 'string',
  boolean: 'boolean',
  'integer[]': 'int_array',
  'integer[][]': 'int_array_2d',
  'string[]': 'string_array',
  'character[]': 'char_array',
  'character[][]': 'char_array_2d',
  'list<integer>': 'int_list',
  'list<list<integer>>': 'int_list_2d',
  'list<string>': 'string_list',
  'list<list<string>>': 'string_list_2d',
  TreeNode: 'tree_node',
  ListNode: 'list_node',
  'ListNode[]': 'list_node_array'
}
const PARAM_MAP = { ...BASE_MAP }
const RETURN_MAP = {
  ...BASE_MAP,
  double: 'double',
  void: null,
  'boolean[]': null,
  'long[]': null,
  'long[][]': null,
  'double[]': null,
  'list<boolean>': null,
  'list<double>': null,
  'list<long>': null,
  'list<list<long>>': null,
  'list<TreeNode>': null,
  NestedInteger: null,
  DataFrame: null
}

const SNIPPET_KEYS = { java: 'java', python3: 'python', python: 'python', cpp: 'cpp', csharp: 'csharp', rust: 'rust', golang: 'go', typescript: 'typescript' }
const WANT_LANGS = new Set(['java', 'python', 'cpp', 'csharp', 'rust', 'go', 'typescript'])

const td = new TurndownService({
  headingStyle: 'atx',
  codeBlockStyle: 'fenced',
  bulletListMarker: '-',
  emDelimiter: '*',
  hr: '---'
})
td.use(gfm)
td.addRule('supToCaret', {
  filter: 'sup',
  replacement: (content) => '^' + String(content).trim()
})

const decode = (s) => s
  .replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&quot;/g, '"')
  .replace(/&#39;/g, "'").replace(/&nbsp;/g, ' ').replace(/&amp;/g, '&')

const EXAMPLE_HEADER_RE = /<strong[^>]*>Example \d+:?\s*<\/strong>/gi
const CONSTRAINTS_RE = /<strong[^>]*>Constraints:?\s*<\/strong>/gi

function exampleHeaderPositions(html) {
  EXAMPLE_HEADER_RE.lastIndex = 0
  const pos = []
  let m
  while ((m = EXAMPLE_HEADER_RE.exec(html))) pos.push(m.index)
  return pos
}

function constraintsPos(html, after) {
  CONSTRAINTS_RE.lastIndex = 0
  let m
  while ((m = CONSTRAINTS_RE.exec(html))) if (m.index > after) return m.index
  return -1
}

function stripExamples(html) {
  const pos = exampleHeaderPositions(html)
  if (!pos.length) return html
  const cons = constraintsPos(html, pos[0])
  return html.slice(0, pos[0]) + html.slice(cons === -1 ? html.length : cons)
}

function innerToLines(inner) {
  return decode(
    inner
      .replace(/<\/p>/gi, '\n')
      .replace(/<br\s*\/?>/gi, '\n')
      .replace(/<\/pre>/gi, '\n')
      .replace(/<[^>]+>/g, '')
  ).split('\n').map((s) => s.trim()).filter(Boolean)
}

function linesToExample(lines) {
  const ex = {}
  let inExp = false
  let inInput = false
  let inOutput = false
  const exp = []
  const inputParts = []
  const flushInput = () => { if (inputParts.length) ex.input = inputParts.join(' '); inputParts.length = 0 }
  for (const line of lines) {
    if (/^Input:\s*/i.test(line)) {
      flushInput()
      inputParts.push(line.replace(/^Input:\s*/i, '').trim())
      inInput = true
      inExp = false
      inOutput = false
    } else if (/^Output:\s*/i.test(line)) {
      flushInput()
      ex.output = line.replace(/^Output:\s*/i, '').trim()
      inInput = false
      inExp = false
      inOutput = true
    } else if (/^Explanation:\s*/i.test(line)) {
      flushInput()
      const r = line.replace(/^Explanation:\s*/i, '')
      if (r) exp.push(r)
      inExp = true
      inInput = false
      inOutput = false
    } else if (inExp) {
      exp.push(line)
    } else if (inOutput) {
      if (/^[^A-Za-z]|^(true|false|null)/.test(line)) ex.output += line
      else inOutput = false
    } else if (inInput) {
      inputParts.push(line)
    }
  }
  flushInput()
  if (ex.input === undefined && ex.output === undefined) return null
  if (exp.length) ex.explanation = exp.join(' ')
  return ex
}

function parseExamples(html) {
  const out = []
  const pos = exampleHeaderPositions(html)
  if (pos.length) {
    for (let i = 0; i < pos.length; i++) {
      const segEnd = i + 1 < pos.length ? pos[i + 1] : (constraintsPos(html, pos[i]) === -1 ? html.length : constraintsPos(html, pos[i]))
      const ex = linesToExample(innerToLines(html.slice(pos[i], segEnd)))
      if (ex) out.push(ex)
    }
    return out
  }
  // fallback: no example headers (legacy markup)
  const re = /<pre>([\s\S]*?)<\/pre>|<div class="example-block"[^>]*>([\s\S]*?)<\/div>/gi
  let m
  while ((m = re.exec(html))) {
    const ex = linesToExample(innerToLines(m[1] ?? m[2]))
    if (ex) out.push(ex)
  }
  return out
}

function slugifyTag(name) {
  return String(name).toLowerCase().replace(/\s+/g, '-')
}

// store raw (no JSON.parse) for arrays / list-likes / long scalars (precision)
function parseTestValue(raw, lcType) {
  const t = raw.trim()
  if (lcType === 'long') return raw
  if (t.startsWith('[')) return raw
  try {
    return JSON.parse(t)
  } catch {
    return t.startsWith('"') && t.endsWith('"') ? t.slice(1, -1) : t
  }
}

function buildTestCases(question, meta) {
  const params = meta.params || []
  if (!params.length) return null
  const lines = (question.exampleTestcases || '').split('\n').filter((l, i, a) => !(l === '' && i === a.length - 1))
  const out = []
  for (let i = 0; i + params.length <= lines.length; i += params.length) {
    const tc = {}
    for (let j = 0; j < params.length; j++) {
      const p = params[j]
      if (!p || !p.name) return null
      tc[p.name] = parseTestValue(lines[i + j] ?? '', p.type)
    }
    out.push(tc)
  }
  return out.length ? out : null
}

function buildStatement(html) {
  try {
    const md = td.turndown(stripExamples(html)).trim()
    return md.replace(/<=/g, '≤').replace(/>=/g, '≥').trim() + '\n'
  } catch {
    return decode(html.replace(/<[^>]+>/g, '')).trim() + '\n'
  }
}

function buildMetadata(q, meta, examples, allTests, cat) {
  const params = meta.params.map((p) => ({ name: p.name, type: PARAM_MAP[p.type] }))
  const starterCode = {}
  for (const s of q.codeSnippets || []) {
    const key = SNIPPET_KEYS[s.langSlug]
    if (key && WANT_LANGS.has(key) && !starterCode[key]) starterCode[key] = s.code
  }
  const tcCount = Math.min(Math.max(3, examples.length), allTests.length)
  return {
    id: q.titleSlug,
    title: `${q.questionFrontendId}. ${q.title}`,
    difficulty: q.difficulty,
    link: `https://leetcode.com/problems/${q.titleSlug}/`,
    category: cat,
    examples,
    starterCode,
    testCases: allTests.slice(0, tcCount),
    functionName: meta.name,
    params,
    outputType: RETURN_MAP[meta.return.type],
    hints: q.hints || []
  }
}

// ---------- main ----------
const slugs = fs.readdirSync(CACHE).filter((f) => f.endsWith('.json') && !f.startsWith('_')).map((f) => f.slice(0, -5))
// original repo problems (with Marker.java) are never overwritten; generated dirs have no Marker yet
const existing = new Set(
  fs.readdirSync(PROBLEMS).filter((d) => {
    if (!fs.existsSync(path.join(PROBLEMS, d, 'Marker.java'))) return false
    try { JSON.parse(fs.readFileSync(path.join(PROBLEMS, d, 'metadata.json'), 'utf8')); return true } catch { return false }
  })
)

const report = { generated: [], existing: [], skipped: {} }
const bump = (reason) => { report.skipped[reason] = (report.skipped[reason] || 0) + 1 }

let processed = 0
for (const slug of slugs) {
  if (only && slug !== only) continue
  if (processed >= limit) break
  processed++

  let q
  try { q = JSON.parse(fs.readFileSync(path.join(CACHE, slug + '.json'), 'utf8')).question } catch { bump('unreadable'); continue }
  if (!q) { bump('no-question'); continue }
  if (existing.has(slug)) { report.existing.push(slug); continue }

  if (!q.content || q.isPaidOnly) { bump('paid'); continue }
  // zero-width chars sometimes hide inside "Example 1:" headers
  q.content = q.content.replace(/[​-‏﻿­]/g, '')
  let meta = q.metaData
  try { meta = typeof meta === 'string' ? JSON.parse(meta) : meta } catch { bump('bad-meta'); continue }
  if (!meta) { bump('bad-meta'); continue }

  if (meta.database) { bump('sql'); continue }
  if (meta.classname || meta.systemdesign) { bump('systemdesign'); continue }
  if (meta.manual) { bump('manual'); continue }
  if (!meta.name || !meta.params || !meta.return || !meta.return.type) { bump('no-function'); continue }
  if (meta.return.type === 'void') { bump('void'); continue }
  if (meta.params.some((p) => !p || !p.name || !(p.type in PARAM_MAP) || PARAM_MAP[p.type] === null)) {
    const bad = meta.params.find((p) => !p || !p.name || !(p.type in PARAM_MAP) || PARAM_MAP[p.type] === null)
    bump('param-type:' + (bad && bad.type ? bad.type : '?'))
    continue
  }
  if (!(meta.return.type in RETURN_MAP) || RETURN_MAP[meta.return.type] === null) {
    bump('return-type:' + meta.return.type)
    continue
  }

  const allTests = buildTestCases(q, meta)
  if (!allTests) { bump('no-testcases'); continue }

  const examples = parseExamples(q.content)
  const cat = q.topicTags && q.topicTags[0] ? slugifyTag(q.topicTags[0].name) : 'misc'
  const metadata = buildMetadata(q, meta, examples, allTests, cat)
  const statement = buildStatement(q.content)

  if (!dry) {
    const dir = path.join(PROBLEMS, slug)
    fs.mkdirSync(dir, { recursive: true })
    fs.writeFileSync(path.join(dir, 'statement.md'), statement, 'utf8')
    fs.writeFileSync(path.join(dir, 'metadata.json'), JSON.stringify(metadata, null, 2) + '\n', 'utf8')
    fs.writeFileSync(path.join(dir, 'official-tests.json'), JSON.stringify(allTests, null, 2) + '\n', 'utf8')
  }
  report.generated.push(slug)
}

// ---------- courseinfo ----------
let courseProblems = null
if (!dry) {
  const course = {
    title: 'LeetCode',
    description: 'All free LeetCode problems with supported test formats, grouped by difficulty. Paid, design/system-design, SQL/pandas, JS-only, and unsupported-signature problems are excluded.',
    'category-order': ['easy', 'medium', 'hard'],
    'problems-of-category': { easy: [], medium: [], hard: [] }
  }
  const dirs = fs.readdirSync(PROBLEMS)
  const entries = []
  for (const d of dirs) {
    let m
    try { m = JSON.parse(fs.readFileSync(path.join(PROBLEMS, d, 'metadata.json'), 'utf8')) } catch { continue }
    const diff = String(m.difficulty || '').toLowerCase()
    if (!['easy', 'medium', 'hard'].includes(diff)) continue
    const n = parseInt(String(m.title || ''), 10)
    entries.push({ slug: d, diff, n: Number.isNaN(n) ? 1e9 : n })
  }
  entries.sort((a, b) => a.n - b.n || a.slug.localeCompare(b.slug))
  for (const e of entries) course['problems-of-category'][e.diff].push(e.slug)
  fs.mkdirSync(COURSE_DIR, { recursive: true })
  fs.writeFileSync(path.join(COURSE_DIR, 'courseinfo.json'), JSON.stringify(course, null, 2) + '\n', 'utf8')
  courseProblems = Object.values(course['problems-of-category']).reduce((a, b) => a + b.length, 0)
}

const summary = {
  generated: report.generated.length,
  existing: report.existing.length,
  courseProblems,
  skipped: report.skipped
}
console.log(JSON.stringify(summary, null, 2))
fs.writeFileSync(path.join(os.tmpdir(), 'lc-generate-report.json'), JSON.stringify(report, null, 2))
