#!/usr/bin/env node
// Lint problems/<slug>/official-tests.json against metadata.json:
//   - keys of each entry exactly match metadata.params names
//   - value types match param types (array -> string starting "[", int/long/double -> number,
//     string/char -> string, bool -> boolean)
//   - no exact-duplicate entries within a file
//   - prints per-slug test count vs target 8
//   - --baseline <file>: pre-existing errors found in the baseline are suppressed
//     (baseline-locked); exit 1 only for NEW errors — the hard gate is verify-markers
// Exit 0 = no new errors / exit 1 = new per-slug errors printed. Count-vs-8 is informational only.
import fs from 'fs'
import path from 'path'

const PROBLEMS = path.resolve('problems')
const TARGET = 8
function fail(msg) { console.error(msg); process.exit(1) }
const onlyIdx = process.argv.indexOf('--only')
if (onlyIdx >= 0 && !process.argv[onlyIdx + 1]) fail('--only requires a comma-separated slug list')
const ONLY = onlyIdx < 0 ? null : new Set(process.argv[onlyIdx + 1].split(',').filter(Boolean))
const baseIdx = process.argv.indexOf('--baseline')
if (baseIdx >= 0 && !process.argv[baseIdx + 1]) fail('--baseline requires a file path')
const BASELINE = baseIdx < 0 ? null : process.argv[baseIdx + 1]

// parse "slug: N tests" headers + indented "  ERROR ..." lines into Map<slug, Set<message>>
function loadBaseline(file) {
  const buf = fs.readFileSync(file)
  // PowerShell `>` redirection writes UTF-16LE with BOM — accept both encodings
  let text = buf[0] === 0xff && buf[1] === 0xfe ? buf.subarray(2).toString('utf16le') : buf.toString('utf-8')
  if (text.charCodeAt(0) === 0xfeff) text = text.slice(1)
  const map = new Map()
  let cur = null
  for (const line of text.split(/\r?\n/)) {
    const h = line.match(/^([^:]+): \d+ tests/)
    if (h) { cur = h[1]; map.set(cur, new Set()); continue }
    const e = line.match(/^\s+ERROR (.*)$/)
    if (e && cur) map.get(cur).add(e[1])
  }
  return map
}
const baseline = BASELINE ? loadBaseline(BASELINE) : null

function jsType(v) {
  if (v === null) return 'null'
  if (Array.isArray(v)) return 'array'
  return typeof v
}

// returns null when ok, else a short reason
function typeError(pType, v) {
  if (pType === 'int' || pType === 'long' || pType === 'double') {
    return typeof v === 'number' ? null : `expected number, got ${jsType(v)}`
  }
  if (pType === 'string' || pType === 'char') {
    return typeof v === 'string' ? null : `expected string, got ${jsType(v)}`
  }
  if (pType === 'bool' || pType === 'boolean') {
    return typeof v === 'boolean' ? null : `expected boolean, got ${jsType(v)}`
  }
  // arrays / list_node / tree_node / graph_node / all other types: JSON string form
  if (typeof v === 'string' && v.startsWith('[')) return null
  return `expected string starting '[', got ${jsType(v)}`
}

// stable key for exact-duplicate detection (top-level key order normalized)
function entryKey(o) {
  return JSON.stringify(Object.fromEntries(Object.entries(o).sort(([a], [b]) => (a < b ? -1 : a > b ? 1 : 0))))
}

let files = 0
let totalTests = 0
let errorFiles = 0
let suppressed = 0
let under8 = 0
const seen = new Set()

for (const d of fs.readdirSync(PROBLEMS).sort()) {
  if (ONLY && !ONLY.has(d)) continue
  seen.add(d)
  const dir = path.join(PROBLEMS, d)
  const metaPath = path.join(dir, 'metadata.json')
  const testsPath = path.join(dir, 'official-tests.json')
  const errors = []
  let entries = []
  let paramNames = []
  let paramTypes = {}

  if (!fs.existsSync(metaPath)) {
    errors.push('missing metadata.json')
  } else {
    try {
      const meta = JSON.parse(fs.readFileSync(metaPath, 'utf-8'))
      const params = meta.params ?? []
      paramNames = params.map((p) => p.name)
      paramTypes = Object.fromEntries(params.map((p) => [p.name, p.type]))
      if (!paramNames.length) errors.push('metadata.params empty')
    } catch (e) {
      errors.push(`error reading metadata.json: ${e.message}`)
    }
  }
  if (!fs.existsSync(testsPath)) {
    errors.push('missing official-tests.json')
  } else {
    try {
      const arr = JSON.parse(fs.readFileSync(testsPath, 'utf-8'))
      if (!Array.isArray(arr)) errors.push('official-tests.json not an array')
      else entries = arr
    } catch (e) {
      errors.push(`error reading official-tests.json: ${e.message}`)
    }
  }

  if (!errors.length) {
    const want = new Set(paramNames)
    const seen = new Map()  // entryKey -> index
    for (let i = 0; i < entries.length; i++) {
      const tc = entries[i]
      if (!tc || typeof tc !== 'object' || Array.isArray(tc)) {
        errors.push(`entry #${i + 1}: not an object`)
        continue
      }
      const keys = Object.keys(tc)
      const extra = keys.filter((k) => !want.has(k))
      const missing = paramNames.filter((k) => !keys.includes(k))
      if (extra.length || missing.length) {
        errors.push(`entry #${i + 1}: keys mismatch — extra [${extra.join(', ')}], missing [${missing.join(', ')}]`)
      }
      for (const name of paramNames) {
        if (!(name in tc)) continue
        const why = typeError(paramTypes[name], tc[name])
        if (why) errors.push(`entry #${i + 1}: ${name}[${paramTypes[name]}] ${why}`)
      }
      const key = entryKey(tc)
      if (seen.has(key)) errors.push(`entry #${i + 1}: exact duplicate of entry #${seen.get(key) + 1}`)
      else seen.set(key, i)
    }
  }

  files++
  totalTests += entries.length
  if (entries.length < TARGET) under8++
  console.log(`${d}: ${entries.length} tests (target ${TARGET})`)
  const known = baseline?.get(d) ?? null
  let newCount = 0
  for (const e of errors) {
    if (known && known.has(e)) { suppressed++; continue }
    newCount++
    console.log(`  ERROR ${e}`)
  }
  if (newCount) errorFiles++
}

// C1 guard: a typo'd/missing --only slug must never yield a vacuous green exit
const unstaged = ONLY ? [...ONLY].filter((s) => !seen.has(s)) : []
if (unstaged.length) console.error(`no such problem dir: ${unstaged.join(', ')}`)
console.log(`summary: ${files} files, ${totalTests} tests, ${errorFiles} files with new errors, ${suppressed} baseline-suppressed, ${under8} under ${TARGET} (informational)`)
process.exit(errorFiles || unstaged.length ? 1 : 0)
