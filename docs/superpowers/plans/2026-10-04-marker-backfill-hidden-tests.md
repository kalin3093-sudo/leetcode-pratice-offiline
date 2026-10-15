# Marker Backfill (G1) + Hidden-Test Expansion (G2) — content remediation round

**Date:** 2026-10-04
**Repo:** `C:\Users\LENOVO\Downloads\cojudge-main` (NOT a git repository — no commit steps; gates are `npx vitest run` + `npm run check`)
**Prerequisite:** `docs/superpowers/plans/2026-10-04-leetcode-offline-platform.md` complete (13/13 tasks, final report delivered)
**Approval:** requirements-audit remediation greenlit by user ("yes")

## Problem

| Ref | Gap | Evidence | Root cause | Fix |
|-----|-----|----------|-----------|-----|
| G1 | 549/2941 problems verdict **Wrong Answer with blank Expected for every submission**, even correct code | `markerless-check.mjs`: correct python on `check-if-all-1s-are-at-least-length-k-places-away` → `isCorrect:false`, `expected:""`, verdict `Wrong Answer`; `submit/+server.ts:228-235` catch sets `isCorrect:false` for all cases | judging goes through `getMarkerResponses` (`run/+server.ts:187`, `submit/+server.ts:220`); `Marker.java` missing → `markerRunner.ts:43` ENOENT → catch fallback | backfill `Marker.java` for all 549 |
| G2 | 2692/2941 `official-tests.json` byte-identical to `metadata.testCases` → Submit re-runs samples; only 204 problems have ≥8 tests (2695 have <5); 12923 official tests total | file diffs + counts | official tests store inputs only (expected computed by Marker at runtime) and were never expanded | append input-only tests until ≥8/problem |

Both fixes are **content-only** — zero `src/` changes.

## Decisions

1. **Marker backfill = LLM-authored per problem.** Seed corpus `lc-solutions.parquet` covers only 64/549 with Java — not a bulk pipeline.
2. **G2 target: ≥8 official tests per problem.** If constraints genuinely yield fewer distinct valid cases, ≥6 allowed with a recorded exception (slug + reason in final report). Existing entries are never modified.
3. New tests are **inputs only**, value format copied from existing entries in the same file (arrays as JSON strings, ints as numbers, strings bare). No `_isLargeTest` additions this round.
4. **Verification = `scripts/verify-markers.mjs`** (stages harness, asserts `isCorrect == true` per case, `--only <slugs>` subsets, `VERIFY_HOST=1` fast local-JDK loop, docker for full runs). Extended with `--tests official` mode (see Task 1).
5. Batches are pre-generated list files under `%TEMP%\opencode\mround\batches\` so every subagent runs the same two gate commands.

## Task 1 — Tooling (1 implementer, then spec-reviewer + code-quality-reviewer)

All in `scripts/`, no `src/` changes:

1. **`scripts/verify-markers.mjs` extensions:**
   - `--tests official`: pair source = `official-tests.json` (direct tc objects, no example parsing). `testCall` prints `isCorrect(fullParam, <fn>(fullParam))` self-check on line 1 (checker must accept the reference's own output; exception → `false`) and the existing `display(fn(fullParam))` line 2. Existing wrong-detection (every block's first line must be `true`) applies unchanged.
   - default mode **byte-identical** to current behavior (oracle check below); add a non-fatal `refWarn` list: for examples whose `display(ref(params))` ≠ known expected output, record slug + both strings (catches wrong reference methods that property checkers would mask; multi-answer order diffs expected — reviewer eyeballs, not a hard failure).
   - `--report <path>` override for batch runs (never clobber `%TEMP%\lc-verify-report.json`; `--only` pid-unique behavior kept).
2. **`scripts/lint-tests.mjs`:** per (optionally `--only`) `official-tests.json`: keys exactly match `metadata.params` names; value types match param types (array → string starting `[`; int/long → number; string/char → string; bool → boolean); no exact-duplicate entries within file; prints count vs target 8. Exit 0 clean / 1 with per-slug errors.
3. **`scripts/make-batches.mjs`:** writes `%TEMP%\opencode\mround\batches\`:
   - `markers-all.txt` = the 549 marker-less slugs → `markers-01..16.txt` (~35 each)
   - `tests-all.txt` = all 2941 (marker-present first so Task 3 can start early) → `tests-01..NN.txt` (50 each)
4. **Oracle checks (record outputs in report):**
   - default staging for 5 known slugs → `Main.java`/`pairs.json` byte-identical to archived `%TEMP%\lc-verify\p\<slug>\`
   - `VERIFY_HOST=1 node scripts/verify-markers.mjs --only two-sum,add-digits` → RUN_OK, wrongCount 0
   - `VERIFY_HOST=1 node scripts/verify-markers.mjs --tests official --only two-sum` → RUN_OK, wrongCount 0
   - `node scripts/lint-tests.mjs` full baseline run → saved report
5. **Gates:** `npx vitest run` 186/186, `npm run check` 10 errors/30 warnings. Diff vs pristine baseline copy: only `scripts/*` + this plan file.

## Task 2 — G1: `Marker.java` for 549 problems (~16 batches × 35, waves of 4)

Per batch (implementer subagent):

- Read `docs/ADD_PROBLEMS.md` §4 (Marker contract, :205-260) and §6 class problems (:354-390) first; reference examples `problems/add-digits/Marker.java` (simple) and `problems/two-sum/Marker.java` (multi-answer property checker).
- For each slug in the batch file: read `metadata.json` (params/functionName/outputType), `statement.md`, examples → author `problems/<slug>/Marker.java`:
  - reference method named `functionName` with matching signature + `isCorrect(<params>, <output>)` per spec
  - **multi-valid-answer problems → property checker** (validate structural constraints of the user's answer; never exact-compare against one reference ordering)
  - **class/design problems → `solve` dispatcher + inner class** (§6)
  - `ListNode`/`TreeNode`/`GraphNode` helpers are on the harness classpath — follow existing marker conventions
- **Gate per batch:** `VERIFY_HOST=1 node scripts/verify-markers.mjs --only <batch slugs>` → all RUN_OK, wrongCount 0. Fix and rerun (≤2 rounds); report residuals honestly.
- Never touch `official-tests.json`, `metadata.json`, statements, `src/`.

Phase gates: spec-reviewer spot-checks markers across batches — especially multi-answer strictness ("would this checker reject a WRONG output?") and the `refWarn` list from Task 1; code-quality-reviewer samples diffs (boring Java, no dead code, no speculative helpers).

## Task 3 — G2: expand official tests to ≥8 (~55 batches × 50, waves of 5)

Precondition per slug: `Marker.java` exists. Marker-present slugs may start immediately after Task 1; the 549 wait for Task 2.

Per batch (implementer subagent):

- For each slug: read statement + constraints + existing official tests + `metadata.params`; append new test objects until count ≥8:
  - inputs strictly within stated constraints, distinct from existing (not mere reorderings)
  - edge cases first (bounds, single/empty, duplicates, sign extremes where allowed), then mid-size
  - value format copied from existing entries in the same file
  - **never modify existing entries**; never change examples/metadata/statements
- **Gate per batch:**
  1. `node scripts/lint-tests.mjs --only <batch slugs> --baseline %TEMP%\opencode\mround\lint-baseline.txt` → **no new errors vs baseline** (169 pre-existing dirty slugs are baseline-locked: documented extra keys like `_isLargeTest`, string-typed ints/longs, `@javascript:` values, 3 dupe files — harness tolerates them, do not fix or worsen them; the hard correctness gate is #2)
  2. `VERIFY_HOST=1 node scripts/verify-markers.mjs --tests official --only <batch slugs>` → all RUN_OK, wrongCount 0 (reference handles every input; checker accepts reference output). A RUN_FAIL on a new test means the input is invalid for the reference → regenerate that test; do not weaken the marker.
- Report per-slug final counts + any exceptions (<8 with reason).

Phase gates: spec-reviewer verifies sample new inputs against statement constraints and reviews the exceptions list + lint baseline.

## Task 4 — Full verification (docker, both modes)

1. `node scripts/verify-markers.mjs` (examples mode, all 2941) → report: 0 compileFail / 0 runFail / 0 wrongCount.
2. `node scripts/verify-markers.mjs --tests official --report %TEMP%\lc-verify-official.json` → same, covering every official test including the new ones.
3. Attach summary to this plan; keep raw reports in `%TEMP%\`.

## Task 5 — Acceptance (report to user)

1. **Gates:** `npx vitest run` 186/186; `npm run check` 10 errors/30 warnings (unchanged baselines).
2. **G1 E2E (the proof):** rerun `node %TEMP%\opencode\markerless-check.mjs` → verdict **Accepted**, `expected:"true"` (flips from Wrong Answer / `""`).
3. **Regression:** `node %TEMP%\opencode\fault-inject.mjs` → 9/9.
4. **G2 E2E:** on one expanded problem — correct solution Submit → Accepted with passedTc ≥8 (previously ≤4); wrong solution → WA with Expected filled on a hidden non-sample case.
5. **Offline:** run one backfilled problem's harness in `docker run --network none` → RUN_OK.
6. **Counts:** markers 2941/2941; official tests total ≥ 12923 + new; ≥8-test problems = 2941 − exceptions.
7. **Diff audit** vs pristine baseline copy (`git diff --no-index`): changed files ⊆ `problems/**/Marker.java`, `problems/**/official-tests.json`, `scripts/{verify-markers,lint-tests,make-batches}.mjs`, plan/docs. Nothing else.
8. **Final report:** G1/G2 summary table with evidence, residuals/exceptions, deferred notes.

## Risks & mitigations

| Risk | Mitigation |
|------|-----------|
| Multi-answer marker too strict (rejects valid alternate answers) | batch rule: property checkers; spec-reviewer spot-check; E2E #4 wrong-solution sanity |
| Marker too weak (accepts wrong answers) | examples gate = `isCorrect(knownExpected, …)` must be true; review spot-checks; E2E wrong solution must fail |
| Wrong reference method masked by property checker | `refWarn` list in examples mode (Task 1) + reviewer eyeball + E2E Expected spot-check |
| Generated tests violate constraints | reference crash/timeout → RUN_FAIL hard gate; lint format; constraint spot-check in phase review |
| Scale (16 + 55 batches) fatigue | pre-generated batch files, identical two-command gate per batch, honest residual reporting over silent skips |
| Batch gate needs JDK | local `H:/javajdk/bin` via `VERIFY_HOST=1`; docker only for Task 4 |

## Out of scope (recorded, deferred)

`parseExampleTc` param-pair bug; per-case `timeMs`; playground verdict UI; CLI `.java` 400; `progressValidation.ts`; statement rewrites; any `src/` judge changes (none expected this round).
