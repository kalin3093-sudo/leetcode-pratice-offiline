#!/usr/bin/env node
// Analyze salvaged verifier output (from %TEMP%/lc-allp.tar) -> %TEMP%/lc-verify-report.json
import fs from 'fs'
import path from 'path'
import os from 'os'
import { execFileSync } from 'child_process'

const EX = path.join(os.tmpdir(), 'lc-allp')
const TAR = path.join(os.tmpdir(), 'lc-allp.tar')
const REPORT = path.join(os.tmpdir(), 'lc-verify-report.json')

fs.rmSync(EX, { recursive: true, force: true })
fs.mkdirSync(EX, { recursive: true })
execFileSync('tar', ['-xf', TAR, '-C', EX], { stdio: 'ignore' })

const results = {}
for (const line of fs.readFileSync(path.join(EX, 'results.txt'), 'utf-8').split('\n')) {
  if (!line.trim()) continue
  const [slug, st] = line.split('\t')
  results[slug] = st
}

const wrong = []
const runFail = {}
const compileFail = {}
const errSig = {}
let runOkCount = 0

for (const [slug, st] of Object.entries(results)) {
  const dir = path.join(EX, 'p', slug)
  if (st === 'RUN_OK') {
    runOkCount++
    const log = fs.existsSync(path.join(dir, 'run.log')) ? fs.readFileSync(path.join(dir, 'run.log'), 'utf-8') : ''
    const blocks = log.split('---').map((b) => b.trim()).filter(Boolean)
    const firsts = blocks.map((b) => b.split('\n')[0].trim())
    if (!firsts.length) wrong.push({ slug, reason: 'empty-output', log: log.slice(0, 500) })
    else if (firsts.some((f) => f !== 'true')) wrong.push({ slug, reason: 'isCorrect-false', log: log.slice(0, 800) })
  } else if (st === 'COMPILE_FAIL') {
    const clog = fs.existsSync(path.join(dir, 'compile.log')) ? fs.readFileSync(path.join(dir, 'compile.log'), 'utf-8') : ''
    compileFail[slug] = clog.slice(0, 800)
    const m = clog.match(/error: .*/)
    const sig = (m ? m[0] : clog.split('\n').find((l) => l.trim()) || 'unknown').replace(/\d+/g, 'N').slice(0, 160)
    errSig[sig] = (errSig[sig] || 0) + 1
  } else {
    const rlog = fs.existsSync(path.join(dir, 'run.log')) ? fs.readFileSync(path.join(dir, 'run.log'), 'utf-8') : ''
    runFail[slug] = rlog.slice(0, 800)
  }
}

const report = {
  total: Object.keys(results).length,
  runOk: runOkCount,
  wrongCount: wrong.length,
  compileFailCount: Object.keys(compileFail).length,
  runFailCount: Object.keys(runFail).length,
  compileErrorSignatures: Object.fromEntries(Object.entries(errSig).sort((a, b) => b[1] - a[1])),
  wrong,
  compileFail,
  runFail
}
fs.writeFileSync(REPORT, JSON.stringify(report, null, 2))
console.log(JSON.stringify({
  total: report.total, runOk: report.runOk, wrongCount: report.wrongCount,
  compileFailCount: report.compileFailCount, runFailCount: report.runFailCount,
  signatures: report.compileErrorSignatures,
  wrongSample: wrong.slice(0, 8).map((w) => `${w.slug} (${w.reason})`),
  runFailSample: Object.keys(runFail).slice(0, 8)
}, null, 1))
