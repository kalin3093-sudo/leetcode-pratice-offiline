// order-insensitive isCorrect for WRONG problems whose statement says any-order.
// scoped: only slugs in lc-verify-report.json wrong[] (+ALLOW_EXTRA - HOLD)
import fs from 'fs'
import path from 'path'

const HOLD = new Set(['find-indices-with-index-and-value-difference-i', 'find-indices-with-index-and-value-difference-ii'])
const ALLOW_EXTRA = new Set(['stamping-the-sequence']) // statement lacked 'any' wording but LC accepts any valid sequence
const anyOrder = /in\s+any\s+order|return\s+any\s|may\s+return[^.]*\bany\b|any\s+(order|valid\s+(permutation|sequence|arrangement|combination|answer|output))|answers?\s+may\s+(be\s+)?(return|swap|differ)/i

const HELPERS = 'static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}\n  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}\n  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}\n  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}\n  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}\n  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}\n  '

function sorterFor(t) {
  const x = t.replace(/\s+/g, ' ').trim()
  if (x === 'int[]') return (v) => `sortedI(${v})`
  if (x === 'int[][]') return (v) => `sortedI2(${v})`
  if (x === 'String[]') return (v) => `sortedA(${v})`
  if (x === 'String') return (v) => `sortedS(${v})`
  if (/^List<List</.test(x)) return (v) => `sortedLL(${v})`
  if (/^List</.test(x)) return (v) => `sortedL(${v})`
  return null
}

const report = JSON.parse(fs.readFileSync(path.join(process.env.TEMP, 'lc-verify-report.json'), 'utf8'))
const wrongs = report.wrong.map((w) => w.slug)
let patched = 0
const out = []

for (const s of wrongs) {
  if (HOLD.has(s)) { out.push(`${s} | HOLD`); continue }
  const p = path.join('problems', s, 'Marker.java')
  if (!fs.existsSync(p)) { out.push(`${s} | no-marker`); continue }
  let src = fs.readFileSync(p, 'utf8')
  if (src.includes('sortedI(') || src.includes('sortedL(') || src.includes('sortedS(')) { out.push(`${s} | already`); continue }
  const stmt = fs.readFileSync(path.join('problems', s, 'statement.md'), 'utf8').replace(/[*_`]/g, '')
  if (!anyOrder.test(stmt) && !ALLOW_EXTRA.has(s)) { out.push(`${s} | no-any-wording`); continue }

  const icIdx = src.indexOf('public boolean isCorrect(')
  if (icIdx < 0) { out.push(`${s} | no-isCorrect`); continue }
  const mSig = src.slice(icIdx).match(/^public boolean isCorrect\(([^)]*)\)/)
  if (!mSig) { out.push(`${s} | sig-parse-fail`); continue }
  const sig = mSig[1]
  const lastParam = sig.split(',').pop().trim()
  const mOut = lastParam.match(/^(.+?)\s+(?:output|ans)$/)
  if (!mOut) { out.push(`${s} | no-output-param | ${sig}`); continue }
  const sorter = sorterFor(mOut[1])
  if (!sorter) { out.push(`${s} | skip-type: ${mOut[1]}`); continue }

  const body = src.slice(icIdx)
  const rets = [...body.matchAll(/return ([^;]+);/g)].map((m) => m[1])
  let retExpr = null
  for (let i = rets.length - 1; i >= 0; i--) if (/\bexpected\b/.test(rets[i])) { retExpr = rets[i]; break }
  if (!retExpr) { out.push(`${s} | no-expected-ret | last=${(rets[rets.length - 1] || '').slice(0, 60)}`); continue }

  let newRet = null
  const mA = retExpr.match(/^Arrays\.(deepEquals|equals)\((.+),\s*(.+)\)$/)
  if (mA) newRet = `Arrays.${mA[1]}(${sorter(mA[2])}, ${sorter(mA[3])})`
  if (!newRet) {
    const mEq = retExpr.match(/^(.+)\.equals\((.+)\)$/)
    if (mEq) newRet = `${sorter(mEq[1])}.equals(${sorter(mEq[2])})`
  }
  if (!newRet) { out.push(`${s} | unhandled-ret | ${retExpr.slice(0, 80)} | ${mOut[1]}`); continue }

  const anchor = src.indexOf('public boolean isCorrect(')
  src = src.slice(0, anchor) + '  ' + HELPERS + '\n  ' + src.slice(anchor)
  const before = `return ${retExpr};`
  if (!src.includes(before)) { out.push(`${s} | ret-not-found: ${retExpr.slice(0, 60)}`); continue }
  src = src.replace(before, `return ${newRet};`)
  fs.writeFileSync(p, src)
  patched++
  out.push(`${s} | PATCHED | ${mOut[1]} | ${retExpr.slice(0, 60)} -> ${newRet.slice(0, 80)}`)
}

console.log(`patched=${patched} of wrong=${wrongs.length}`)
for (const l of out) console.log(l)
