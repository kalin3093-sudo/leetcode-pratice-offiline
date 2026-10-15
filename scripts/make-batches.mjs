#!/usr/bin/env node
// Pre-generate batch list files under %TEMP%/opencode/mround/batches/ so every subagent
// runs the same gate commands:
//   markers-all.txt -> markers-01..NN.txt (~35 each): the marker-less slugs (Task 2)
//   tests-all.txt   -> tests-01..NN.txt  (50 each): all slugs, marker-present first (Task 3)
import fs from 'fs'
import path from 'path'
import os from 'os'

const PROBLEMS = path.resolve('problems')
const OUT = path.join(os.tmpdir(), 'opencode', 'mround', 'batches')

const all = fs.readdirSync(PROBLEMS).filter((d) => fs.existsSync(path.join(PROBLEMS, d, 'metadata.json'))).sort()
const markers = all.filter((d) => !fs.existsSync(path.join(PROBLEMS, d, 'Marker.java')))
const present = all.filter((d) => fs.existsSync(path.join(PROBLEMS, d, 'Marker.java')))

// purge first: a re-run must not leave stale higher-numbered batches behind.
// ponytail: generate ONCE — batch content depends on which slugs already have markers;
// regenerating while waves are active silently shifts batch membership (lost updates / gaps)
fs.rmSync(OUT, { recursive: true, force: true })
fs.mkdirSync(OUT, { recursive: true })

function writeAll(name, slugs) {
  fs.writeFileSync(path.join(OUT, name), slugs.map((s) => `${s}\n`).join(''))
}

function splitWrite(prefix, slugs, size) {
  const files = []
  for (let i = 0; i < slugs.length; i += size) {
    const n = String(files.length + 1).padStart(2, '0')
    const file = `${prefix}-${n}.txt`
    fs.writeFileSync(path.join(OUT, file), slugs.slice(i, i + size).map((s) => `${s}\n`).join(''))
    files.push(file)
  }
  return files
}

writeAll('markers-all.txt', markers)
const markerFiles = splitWrite('markers', markers, 35)
// marker-present first so Task 3 batches can start before Task 2 finishes
writeAll('tests-all.txt', [...present, ...markers])
const testFiles = splitWrite('tests', [...present, ...markers], 50)

console.log(`out: ${OUT}`)
console.log(`markerless: ${markers.length} -> ${markerFiles[0]}..${markerFiles[markerFiles.length - 1]} (${markerFiles.length} files)`)
console.log(`tests: ${all.length} -> ${testFiles[0]}..${testFiles[testFiles.length - 1]} (${testFiles.length} files)`)
