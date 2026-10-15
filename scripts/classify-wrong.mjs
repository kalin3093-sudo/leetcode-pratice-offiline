// classify verifier wrongs: format vs permutation (multi-answer) vs real wrong
import fs from 'fs'
import path from 'path'
import os from 'os'

const rep = JSON.parse(fs.readFileSync(path.join(os.tmpdir(), 'lc-verify-report.json'), 'utf8'))
const norm = (s) => String(s ?? '').replace(/\s+/g, '').replace(/["\[\]{}]/g, '')
const sortedChars = (s) => norm(s).split('').sort().join('')
const tryJ = (s) => { try { return JSON.parse(s) } catch { return null } }
const sameSet = (a, b) =>
  a.length === b.length &&
  [...a].map((x) => JSON.stringify(x)).sort().join() === [...b].map((x) => JSON.stringify(x)).sort().join()

const cats = { format: [], permute: [], real: [], unknown: [] }
for (const w of rep.wrong) {
  const dir = path.join(os.tmpdir(), 'lc-full', 'p', w.slug)
  let pairs, log
  try {
    pairs = JSON.parse(fs.readFileSync(path.join(dir, 'pairs.json'), 'utf8'))
    log = fs.readFileSync(path.join(dir, 'run.log'), 'utf8')
  } catch { cats.unknown.push(w.slug); continue }
  const blocks = log.split('---').map((b) => b.trim()).filter(Boolean)
  let cat = null
  for (let i = 0; i < blocks.length; i++) {
    const [verdict, ref] = blocks[i].split('\n')
    if (verdict === 'true') continue
    const exp = pairs[i]?.out
    if (ref === exp || norm(ref) === norm(exp)) { cat = 'format'; break }
    const a = tryJ(ref), b = tryJ(exp)
    if (Array.isArray(a) && Array.isArray(b) && sameSet(a, b)) { cat = 'permute'; break }
    // unquoted display lists / any-order content: same character multiset
    if (String(ref).startsWith('[') && sortedChars(ref) === sortedChars(exp)) { cat = 'permute'; break }
    cat = 'real'
  }
  cats[cat ?? 'unknown'].push(w.slug)
}
for (const [k, v] of Object.entries(cats)) console.log(`${k} (${v.length}): ${v.join(', ')}\n`)
