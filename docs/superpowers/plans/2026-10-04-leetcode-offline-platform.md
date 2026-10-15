# LeetCode-Style Offline Platform — C++/Python-Only, Verdicts, Submission Scoreboard, Offline Images

**Date:** 2026-10-04
**Repo:** `C:\Users\LENOVO\Downloads\cojudge-main` (NOT a git repository — no commit steps; gates are `npx vitest run` + `npm run check`)

## Feature Definition

Close the four remaining requirement gaps between current Cojudge behavior and a LeetCode-like
offline judge:

| Ref | Requirement | Gap today | Fix |
|-----|-------------|-----------|-----|
| R2 | Solve in C++ / Python only, `class Solution` | 7-language dropdowns, default `java` (stored settings default to `'java'`) | `USER_LANGUAGES` guard at API + UI selects/defaults with stale-pref coercion |
| R5 | LeetCode verdicts: Accepted / Wrong Answer / Runtime Error / Compile Error / Time Limit Exceeded | Only Accepted / Failed / Failed (TLE) | `verdict` plumbed from job to UI; `StatusType` + labels |
| R6 | Per-test results with expected vs actual; runtime + memory always shown | Submit shows first failing case only, chunks discarded; metrics only shown when Accepted | Submission scoreboard (all chunk rows, case pills, detail pane); metrics gate removed |
| R7 | Fully offline | `ensureTimeInstalled` apt-installs `time` (needs network); stock images lack `/usr/bin/time` → every run would throw exit 127 | Bake `time` into derived images; switch image defaults; README prep steps |

**Out of scope (recorded, deferred):** 549 missing `Marker.java` backfill (separate plan —
datasets not fetched yet); `parseExampleTc` param-pair bug (metadata workarounds green);
per-case `timeMs` in scoreboard (stripped by `parseInternalTiming`; total runtime+memory shown);
playground verdict UI; CLI `getLangFromExt` for `.java` (400 from guard is intended).

## Architecture / Design Decisions (settled)

**Verdict classification (single source of truth in `util.ts`):**

| Signal | Source | Verdict |
|--------|--------|---------|
| `programRunner.compile()` throws (non-timeout) | `phase === 'compile'` | Compile Error |
| `programRunner.run()` throws non-timeout (incl. segfault → exit 139) | `phase === 'run'` | Runtime Error |
| error contains `TIMEOUT_MESSAGE` | catch branch | Time Limit Exceeded |
| results exist, any `isCorrect === false` (incl. `:::ERROR:::` clone error) | `resultsVerdict(results)` | Wrong Answer / Accepted |
| marker-judge catch fallback (all `isCorrect:false`) | same | Wrong Answer (existing swallow, unchanged) |

- Runtime errors are batch-level: harnesses do not catch per-case exceptions, container exits
  non-zero, runner throws — one job error for the whole chunk (attribution: chunk's first case).
  Ceiling: per-case RE attribution would need harness changes in all 3 generators.
- `:::ERROR:::` per-case marker exists only for clone-check (`invalid clone - same object`);
  `run/+server.ts:204` hardcode is accurate, left alone.
- Compile-phase also covers pre-compile setup failures (missing metadata) → labeled Compile
  Error. Ceiling: acceptable, one comment.

**UI state model:**

- New `StatusType` values: `wrong-answer | runtime-error | compile-error`; `tle` label becomes
  `"Time Limit Exceeded"`; `statusFromVerdict(verdict, fallback)` maps API verdicts.
- Submit scoreboard: `submitRows` (concat chunk results with `caseIndex`), `submitVerdict`,
  `submitSelectedIndex`, `submitTotalTc`, `hasSubmitted`, reactive `activeSubmitRow`.
- Detail-pane priority: `submissionFailure` (CE / RE / TLE / start-error — has inputs + error)
  wins over row detail (WA / Accepted — inputs + Your Answer + Expected). Ceiling: when a submit
  also fails, previously-passed rows are summarized but not browsable.
- `Use Test Case` works from both (template = `activeSubmitRow ?? submissionFailure?.testCase`;
  must exclude `caseIndex` from copied input keys).
- Metrics shown whenever `executionTimeMs != null || memoryKB != null` (also after plain Runs —
  `handleRun` previously never assigned them from the response).

**Offline images:**

- `docker/cpp/Dockerfile` already exists (gdb precedent, currently unreferenced) — extend with
  `time`; add `docker/python/Dockerfile`; build `cojudge/cpp:13` + `cojudge/python:3.11`;
  switch the two image constants (env overrides preserved/added).
- `markerRunner.ts` uses `alpine/java:22-jdk` for judging with no `time` wrap and no apt — fine
  offline once the image is pulled.
- Ceiling: in-app pull button only works for Docker Hub images; local images must be built via
  README commands (documented).

## Phase 0 — Baseline Gate (COMPLETE, evidence recorded)

- Round-9 full Docker verify (`node scripts/verify-markers.mjs`, PID 21924,
  `verify9.log`, report `%TEMP%\lc-verify-report.json`): **total 2390, runOk 2390,
  compileFail 0, runFail 0, wrongCount 0, missing 0, wrong []** — all 2390 generated problems
  pass the real judge path. Gate PASSED, no action.
- Pre-work gate for the executor: run `npx vitest run` (baseline: 175 pass) and
  `npm run check`, record the error/warning counts — later tasks must not increase them.
  In PowerShell use `$env:VERIFY_HOST='1'` style env vars, never inline `node -e` regex.

---

## Task 1 — `util.ts`: USER_LANGUAGES, coercion, verdict helpers + unit tests

**File: `src/lib/utils/util.ts`** — insert after line 27 (`export const TIMEOUT_MESSAGE = 'TIMEOUT';`):

```ts
// Platform judges only C++/Python (R2). Single source of truth for API guards + UI.
export const USER_LANGUAGES: ProgrammingLanguage[] = ['cpp', 'python'];

export function coerceUserLanguage(lang: unknown): ProgrammingLanguage {
    return (USER_LANGUAGES as string[]).includes(lang as string) ? (lang as ProgrammingLanguage) : 'cpp';
}

export type Verdict = 'Accepted' | 'Wrong Answer' | 'Runtime Error' | 'Time Limit Exceeded' | 'Compile Error';

export function resultsVerdict(results: { isCorrect: boolean }[]): Verdict {
    return results.every((r) => r.isCorrect) ? 'Accepted' : 'Wrong Answer';
}

// Timeout is assigned directly at the catch site; this maps the non-timeout failure phases.
export function errorVerdict(phase: 'compile' | 'run'): Verdict {
    return phase === 'compile' ? 'Compile Error' : 'Runtime Error';
}
```

**New file: `src/lib/utils/util.test.ts`** (no colocated util tests exist yet):

```ts
import { describe, expect, it } from 'vitest';
import { USER_LANGUAGES, coerceUserLanguage, errorVerdict, resultsVerdict } from './util';

describe('USER_LANGUAGES', () => {
    it('offers only cpp and python', () => {
        expect(USER_LANGUAGES).toEqual(['cpp', 'python']);
    });
});

describe('coerceUserLanguage', () => {
    it('keeps supported languages', () => {
        expect(coerceUserLanguage('cpp')).toBe('cpp');
        expect(coerceUserLanguage('python')).toBe('python');
    });
    it('coerces everything else to cpp (stored settings default to java)', () => {
        expect(coerceUserLanguage('java')).toBe('cpp');
        expect(coerceUserLanguage(undefined)).toBe('cpp');
        expect(coerceUserLanguage('brainfuck')).toBe('cpp');
    });
});

describe('resultsVerdict', () => {
    it('Accepted when every case passes', () => {
        expect(resultsVerdict([{ isCorrect: true }, { isCorrect: true }])).toBe('Accepted');
    });
    it('Wrong Answer when any case fails', () => {
        expect(resultsVerdict([{ isCorrect: true }, { isCorrect: false }])).toBe('Wrong Answer');
    });
});

describe('errorVerdict', () => {
    it('maps compile phase to Compile Error', () => {
        expect(errorVerdict('compile')).toBe('Compile Error');
    });
    it('maps run phase to Runtime Error', () => {
        expect(errorVerdict('run')).toBe('Runtime Error');
    });
});
```

**Verify:** `npx vitest run src/lib/utils/util.test.ts` → new tests green.
**Checkpoint:** If you cannot complete this task exactly as specified, stop and report.

---

## Task 2 — Language guards on all three job-creating POST endpoints

Guard placed at the top of POST, before the `debugLines` branch (debug sessions are language-
gated too). GET pollers are language-free, untouched.

**File: `src/routes/api/run/+server.ts`**

1. Line 15:
```ts
// old
import { TIMEOUT_MESSAGE, type JobStatus } from '$lib/utils/util';
// new
import { TIMEOUT_MESSAGE, USER_LANGUAGES, type JobStatus } from '$lib/utils/util';
```
2. After the destructure (lines 221-222):
```ts
export const POST: RequestHandler = async ({ request }) => {
    const { problemId, language, code, testCases, debugLines } = await request.json();

    if (!USER_LANGUAGES.includes(language)) {
        return json({ error: `Language not supported: ${language}. Allowed: ${USER_LANGUAGES.join(', ')}` }, { status: 400 });
    }
```

**File: `src/routes/api/submit/+server.ts`**

1. Line 15: same import change as above.
2. After line 262 destructure:
```ts
export const POST: RequestHandler = async ({ request }) => {
    const { problemId, language, code, startTcNo } = await request.json();

    if (!USER_LANGUAGES.includes(language)) {
        return json({ error: `Language not supported: ${language}. Allowed: ${USER_LANGUAGES.join(', ')}` }, { status: 400 });
    }
```

**File: `src/routes/api/playground/run/+server.ts`**

1. Line 3: same import change.
2. After lines 74-75:
```ts
export const POST: RequestHandler = async ({ request }) => {
    const { language, code, debugLines } = await request.json();

    if (!USER_LANGUAGES.includes(language)) {
        return json({ error: `Language not supported: ${language}. Allowed: ${USER_LANGUAGES.join(', ')}` }, { status: 400 });
    }
```

**Verify:** `npm run check` — no new errors.

---

## Task 3 — Problems page: default C++ + two-option select

**File: `src/routes/problems/[slug]/+page.svelte`**

1. Line 23:
```svelte
import { getDifficultyClass, USER_LANGUAGES, coerceUserLanguage, type ProgrammingLanguage } from '$lib/utils/util.js';
```
2. Line 72 (settings default is `'java'` — coerce stale prefs):
```ts
let language: ProgrammingLanguage = coerceUserLanguage($userSettingsStorage.preferredLanguage ?? 'java');
```
3. Replace the select options (lines 1101-1107):
```svelte
<option value="cpp">C++</option>
<option value="python">Python</option>
```
(delete the java/typescript/csharp/rust/go options; the `on:change` handler persisting
`preferredLanguage` is unchanged and now persists only valid values)

---

## Task 4 — ExecutionPanel default language

**File: `src/lib/components/ExecutionPanel.svelte`** line 17:
```ts
// old
export let language: ProgrammingLanguage = "java";
// new
export let language: ProgrammingLanguage = "cpp";
```
(The problems page passes `{language}` explicitly; this covers any other caller.)

---

## Task 5 — Playground page: coerce default, restrict select, guard rename inference

**File: `src/routes/playground/+page.svelte`**

1. Line 21:
```ts
import { USER_LANGUAGES, coerceUserLanguage, type ProgrammingLanguage } from '$lib/utils/util.js';
```
2. Line 34:
```ts
let language: ProgrammingLanguage = coerceUserLanguage($userSettingsStorage.playgroundPreferredLanguage ?? 'java');
```
3. Replace select options (lines 7340-7348):
```svelte
<option value="cpp">C++</option>
<option value="python">Python</option>
<option value="plaintext">Plaintext</option>
<option value="markdown">Markdown</option>
```
4. Rename-inference guard (lines 803-812):
```ts
const inferred = inferLanguageFromExtension(finalName);
// ponytail: /api/run 400s are the backstop for other stale stored languages (e.g. lastLanguage)
if (inferred && (USER_LANGUAGES.includes(inferred) || inferred === 'plaintext' || inferred === 'markdown')) {
    setLastLanguage(langFileId, inferred);
    const active = tabs[activeTabId];
    if (active && !isSpecialTabType(active.type) && active.fileId === langFileId && language !== inferred) {
        saveCurrentViewState();
        language = inferred;
        userSettingsStorage.update((s) => ({ ...s, playgroundPreferredLanguage: inferred }));
    }
}
```
(ceiling: ~8 other `language = ...` sites can still surface a stored `'java'`; the API guard
from Task 2 is the backstop — run shows a clear 400 instead of judging)

**Verify after Tasks 1-5:** `npx vitest run` (all green) and `npm run check` (no new errors).

---

## Task 6 — `run/+server.ts`: phase tracking + `verdict` plumbing

**File: `src/routes/api/run/+server.ts`**

1. Line 15 import (extends Task 2's line):
```ts
import { TIMEOUT_MESSAGE, USER_LANGUAGES, errorVerdict, resultsVerdict, type JobStatus, type Verdict } from '$lib/utils/util';
```
2. `RunJob` type (lines 26-35) — add field:
```ts
type RunJob = {
    id: string;
    status: JobStatus;
    createdAt: number;
    results?: RunSuccess;
    timeout?: boolean;
    error?: string;
    executionTimeMs?: number;
    memoryKB?: number;
    verdict?: Verdict;
};
```
3. `executeRun` (line 49):
```ts
async function executeRun(problemId: string, language: string, code: string, testCases: any[], job: RunJob) {
    // ponytail: setup failures before run() are labeled Compile Error
    let phase: 'compile' | 'run' = 'compile';
    try {
```
4. After line 77 (`await programRunner.compile();`) insert:
```ts
        phase = 'run';
```
5. Both success ends — before `job.status = 'completed';` (lines 143-144 merged-marker path and
   lines 207-208 standard path) insert:
```ts
        job.verdict = resultsVerdict(finalResponse);
```
   (each path already has `const finalResponse` in scope; marker-catch fallback feeds it all
   `isCorrect:false` → Wrong Answer, which is the existing swallow behavior)
6. Catch (lines 210-218) becomes:
```ts
    } catch (error: any) {
        if (error && error.toString && error.toString().indexOf(TIMEOUT_MESSAGE) !== -1) {
            job.timeout = true;
            job.verdict = 'Time Limit Exceeded';
            job.status = 'completed';
        } else {
            job.error = `Execution failed: details: ${error}`;
            job.verdict = errorVerdict(phase);
            job.status = 'error';
        }
    }
```
7. GET (lines 250-261) — add `verdict` to all three terminal responses:
```ts
    if (job.timeout) {
        return json({ ready: true, timeout: true, verdict: job.verdict });
    }
    if (job.status === 'error') {
        return json({ ready: true, error: job.error || 'Execution failed', verdict: job.verdict }, { status: 400 });
    }
    return json({
        ready: true,
        results: job.results || [],
        executionTimeMs: job.executionTimeMs,
        memoryKB: job.memoryKB,
        verdict: job.verdict,
    });
```

---

## Task 7 — `submit/+server.ts`: phase tracking + `verdict` plumbing

**File: `src/routes/api/submit/+server.ts`**

1. Line 15 import: same as Task 6 line 15 (drop `USER_LANGUAGES` only if not already added —
   it was added in Task 2; this edit adds `errorVerdict, resultsVerdict, type Verdict`).
2. `SubmitJob` (lines 24-35) — add `verdict?: Verdict;` after `memoryKB?: number;`.
3. `executeSubmit` start (lines 49-51):
```ts
async function executeSubmit(problemId: string, language: string, code: string, startTcNo: number, job: SubmitJob) {
    let timeoutTestcase: any = null;
    // ponytail: setup failures before run() are labeled Compile Error
    let phase: 'compile' | 'run' = 'compile';
    try {
```
4. After line 135 (`await programRunner.compile();`) insert:
```ts
        phase = 'run';
```
5. Early-returns (lines 67-71 and 109-113) — add before `job.status = 'completed';`:
```ts
                job.verdict = 'Accepted';
```
6. Success end (lines 245-247):
```ts
        const accepted = markerResponses.every((m: any) => m.isCorrect);
        job.result = { accepted, totalTc, passedTc: (job.result?.passedTc ? job.result?.passedTc : 0) + testCases.length, results: finalResponse };
        job.verdict = resultsVerdict(finalResponse);
        job.status = 'completed';
```
7. Catch (lines 248-258):
```ts
    } catch (error: any) {
        if (error && error.toString() && error.toString().indexOf(TIMEOUT_MESSAGE) !== -1) {
            job.timeout = true;
            job.timeoutTestCase = timeoutTestcase;
            job.verdict = 'Time Limit Exceeded';
            job.status = 'completed';
        } else {
            job.error = `Submission failed: details: ${error.message || error}`;
            job.errorTestCase = timeoutTestcase;
            job.verdict = errorVerdict(phase);
            job.status = 'error';
        }
    }
```
8. GET (lines 277-288):
```ts
    if (job.timeout) {
        return json({ ready: true, timeout: true, timeoutTestCase: job.timeoutTestCase, verdict: job.verdict });
    }
    if (job.status === 'error') {
        return json({ ready: true, error: job.error || 'Submission failed', errorTestCase: job.errorTestCase, verdict: job.verdict }, { status: 400 });
    }
    return json({
        ready: true,
        ...(job.result || {}),
        executionTimeMs: job.executionTimeMs,
        memoryKB: job.memoryKB,
        verdict: job.verdict,
    });
```
(`job.result` has no `verdict` key — explicit field wins, no clash)

---

## Task 8 — `ProgramRunner.crashMessage` + three run() call sites

Segfaults produce empty stderr; today the runner throws the partial stdout (pre-crash test
output) as the "error", which reads as garbage and never matches `getCrashHint`.

**File: `src/lib/runners/ProgramRunner.ts`** — insert immediately before `static wrapWithMetrics` (line 22):

```ts
static crashMessage(exitCode: number, stderr: string, stdout: string): string {
    if (stderr && stderr.trim()) return stderr;
    const signals: Record<number, string> = {
        134: 'Program aborted (SIGABRT)',
        136: 'Floating point exception (SIGFPE)',
        139: 'Segmentation fault (SIGSEGV)',
    };
    if (signals[exitCode]) return signals[exitCode];
    if (stdout && stdout.trim()) return stdout;
    return `Program exited with code ${exitCode}`;
}
```

**Call sites** (all three classes `extend ProgramRunner`, so the static is in scope; replace
ONLY the `run()` site, leave compile sites alone):

1. `src/lib/runners/CppRunner.ts:118` (compile site is :82 with `stderr || stdout` — do not touch):
```ts
// old
            if (inspect.ExitCode !== 0) throw new Error(cleanedStderr || stdout);
// new
            if (inspect.ExitCode !== 0) throw new Error(ProgramRunner.crashMessage(inspect.ExitCode, cleanedStderr, stdout));
```
2. `src/lib/runners/PythonRunner.ts:95` — same one-line replacement.
3. `src/lib/runners/JavaRunner.ts:140-141` (compile site is :104 — do not touch):
```ts
// old
            if (inspect.ExitCode !== 0) {
                throw new Error(cleanedStderr || stdout);
            }
// new
            if (inspect.ExitCode !== 0) {
                throw new Error(ProgramRunner.crashMessage(inspect.ExitCode, cleanedStderr, stdout));
            }
```

Non-zero exits now carry `Segmentation fault (SIGSEGV)` etc. → `getCrashHint` matches.

**Verify after Tasks 6-8:** `npx vitest run` + `npm run check` (no new errors).

---

## Task 9 — ExecutionPanel part 1: types, labels, `statusFromVerdict`, handleRun

**File: `src/lib/components/ExecutionPanel.svelte`**

1. `StatusType` (lines 28-39) — add three members:
```ts
    type StatusType =
        | "no-status"
        | "sample-tests-passed"
        | "sample-tests-failed"
        | "accepted"
        | "failed"
        | "wrong-answer"
        | "runtime-error"
        | "compile-error"
        | "tle"
        | "running"
        | "sample-tests-failed-tle"
        | "preparing"
        | "pending"
        | "judging";
```
2. `statusToString` (lines 113-140) — add cases and relabel tle:
```ts
            case "wrong-answer":
                return "Wrong Answer";
            case "runtime-error":
                return "Runtime Error";
            case "compile-error":
                return "Compile Error";
            ...
            case "tle":
                return "Time Limit Exceeded";
```
3. New helper directly after `statusToString`:
```ts
    function statusFromVerdict(verdict: string | undefined, fallback: StatusType): StatusType {
        switch (verdict) {
            case "Accepted": return "accepted";
            case "Wrong Answer": return "wrong-answer";
            case "Runtime Error": return "runtime-error";
            case "Compile Error": return "compile-error";
            case "Time Limit Exceeded": return "tle";
            default: return fallback;
        }
    }
```
4. `handleRun` edits:
   - POST !ok branch, line 569: `status = statusFromVerdict(startBody?.verdict, "sample-tests-failed");`
   - poll !ok branch, line 589: `status = statusFromVerdict(body?.verdict, "sample-tests-failed");`
   - timeout branch, line 608: `status = "tle";` (was `"sample-tests-failed-tle"`)
   - results branch, replace lines 611-623 with:
```ts
                if (Array.isArray(body?.results)) {
                    const results = body.results;
                    testCaseResults = results;
                    status = statusFromVerdict(
                        body.verdict,
                        results.every((tc: any) => tc.isCorrect) ? "sample-tests-passed" : "sample-tests-failed",
                    );
                    if (body.executionTimeMs != null) executionTimeMs = body.executionTimeMs;
                    if (body.memoryKB != null) memoryKB = body.memoryKB;
                    break;
                }
```
   - outer `catch` (line 637): leave `status = "sample-tests-failed"` (client/network error).
5. Footer status span classes (lines 2056-2063):
```svelte
                <span
                    class="status"
                    class:correct={status == "accepted" ||
                        status == "sample-tests-passed"}
                    class:incorrect={status == "failed" ||
                        status == "wrong-answer" ||
                        status == "runtime-error" ||
                        status == "compile-error" ||
                        status == "sample-tests-failed" ||
                        status == "sample-tests-failed-tle" ||
                        status == "tle"}
                >
```
   (`.correct` / `.incorrect` are generic CSS classes at :2379-2382 — no CSS additions needed)

---

## Task 10 — ExecutionPanel part 2: scoreboard state + handleSubmit + Use Test Case

**File: `src/lib/components/ExecutionPanel.svelte`**

1. State — insert after line 73 (`let submissionFailure: SubmissionFailure = null;`):
```ts
    // LeetCode-style scoreboard for the last submission (R6)
    let submitRows: any[] = [];
    let submitVerdict: string | null = null;
    let submitSelectedIndex = 0;
    let submitTotalTc = 0;
    let hasSubmitted = false;
    $: activeSubmitRow = submitRows[submitSelectedIndex] ?? null;
```
2. `handleSubmit` start — after line 657 (`submissionFailure = null; // reset previous submission view`):
```ts
        submitRows = [];
        submitVerdict = null;
        submitSelectedIndex = 0;
        submitTotalTc = 0;
        hasSubmitted = true;
```
   (POST !ok block lines 687-702 keeps its existing `type: "failed"` submissionFailure —
   guard 400 has no verdict, fallback label "Failed")
3. poll !ok block (lines 710-736) — replace status/runningMessage lines, keep the
   `submissionFailure` construction:
```ts
                    if (!pollRes.ok) {
                        runningMessage = "";
                        status = statusFromVerdict(body?.verdict, "failed");
                        submitVerdict = statusToString(status) || "Failed";
                        runningMessage = status === "compile-error"
                            ? submitVerdict
                            : `${submitVerdict} on Test ${tcNo + 1}`;
                        // Prepare failure details (unchanged shape)
                        if (body?.timeout && body?.timeoutTestCase) {
```
   (keep the existing `submissionFailure = { type: "tle" ... }` / `{ type: "failed" ... }`
   bodies and `activeMainTab = "submission"; break;` untouched)
4. ready+timeout block (lines 744-758):
```ts
                    if (body?.timeout) {
                        runningMessage = "";
                        status = "tle";
                        submitVerdict = "Time Limit Exceeded";
                        runningMessage = `Time Limit Exceeded on Test ${tcNo + 1}`;
                        submissionFailure = {
                            type: "tle",
                            index: tcNo,
                            testCase: body.timeoutTestCase || null,
                            yourAnswer: null,
                            expectedAnswer: null,
                            error: "Time Limit Exceeded",
                        };
                        activeMainTab = "submission";
                        break;
                    }
```
5. totalTc line (759-761):
```ts
                    if (body.totalTc) {
                        totalTc = body.totalTc;
                        submitTotalTc = body.totalTc;
                    }
```
6. Insert AFTER the metrics accumulation block (762-767), BEFORE `if (body.allAccepted)` —
   accumulate every chunk (this replaces the old discard at 805-807):
```ts
                    if (Array.isArray(body.results)) {
                        submitRows = [
                            ...submitRows,
                            ...body.results.map((r: any, i: number) => ({ ...r, caseIndex: tcNo + i })),
                        ];
                    }
```
7. `allAccepted` branch (768-780) — add inside, before `break`:
```ts
                        submitVerdict = "Accepted";
                        submitSelectedIndex = 0;
                        activeMainTab = "submission";
```
8. `!body.accepted` branch (781-804) — replace the entire body between `if (!body.accepted) {`
   and its closing `break; }`:
```ts
                    if (!body.accepted) {
                        status = "wrong-answer";
                        submitVerdict = "Wrong Answer";
                        const failIdx = submitRows.findIndex((r: any) => !r.isCorrect);
                        submitSelectedIndex = failIdx >= 0 ? failIdx : 0;
                        const failCaseNo = (submitRows[submitSelectedIndex]?.caseIndex ?? tcNo) + 1;
                        runningMessage = `Wrong Answer on Test ${failCaseNo}`;
                        submissionFailure = null;
                        activeMainTab = "submission";
                        break;
                    }
```
   (chunk results were already accumulated in step 6 above this point; old
   `localFailIndex` / `submissionFailure` construction deleted — detail comes from rows)
9. Outer while exit condition (lines 810-814):
```ts
                if (
                    status === "wrong-answer" ||
                    status === "failed" ||
                    status === "accepted" ||
                    status === "tle"
                ) {
                    break;
                }
```
10. client `catch` (817-832) — add after `status = "failed";`:
```ts
                submitVerdict = "Failed";
```
    (network/client error keeps generic "Failed")
11. `useFailedTestCase` (lines 420-438) — use row template and exclude `caseIndex`:
```ts
    function useFailedTestCase() {
        const template = activeSubmitRow ?? submissionFailure?.testCase;
        if (!template) return;
        const newCase: any = {};
        Object.entries(template).forEach(([k, v]) => {
            if (
                k !== "output" &&
                k !== "error" &&
                k !== "logs" &&
                k !== "correctAnswer" &&
                k !== "isCorrect" &&
                k !== "caseIndex"
            )
                newCase[k] = v;
        });
        const pushed = { ...newCase, output: null, logs: "", error: null };
        testCaseResults = [...testCaseResults, pushed];
        activeTestCaseIndex = testCaseResults.length - 1;
        activeMainTab = "testcase";
    }
```

---

## Task 11 — ExecutionPanel part 3: submission view rewrite + metrics ungated

**File: `src/lib/components/ExecutionPanel.svelte`**

1. Replace the whole submission block (lines 1506-1573, from
   `{:else if activeMainTab === "submission" && submissionFailure}` through the closing
   `</div>` before `{:else if activeMainTab === "debugger" && debugState}`) with:

```svelte
        {:else if activeMainTab === "submission" && hasSubmitted}
            <div class="submission-view">
                {#if submitVerdict && !submissionFailure}
                    <div class="summary">
                        <span class="result-title {submitVerdict === 'Accepted' ? 'correct' : 'incorrect'}"
                            >{submitVerdict}{#if submitTotalTc > 0}&nbsp;·&nbsp;Passed {submitRows.filter((r: any) => r.isCorrect).length} / {submitTotalTc}{/if}</span
                        >
                    </div>
                {/if}
                {#if submitRows.length > 0 && !submissionFailure}
                    <div class="case-selector">
                        {#each submitRows as row, i}
                            <div class="case-btn-wrap">
                                <button
                                    class="output btn case-btn"
                                    class:correct={row.isCorrect}
                                    class:incorrect={!row.isCorrect}
                                    class:active={submitSelectedIndex === i}
                                    on:click={() => (submitSelectedIndex = i)}
                                >
                                    Case {row.caseIndex + 1}
                                </button>
                            </div>
                        {/each}
                    </div>
                {/if}
                {#if submissionFailure}
                    <div class="inputs">
                        <div class="result-title">Inputs</div>
                        <div class="kv-list">
                            {#if submissionFailure.testCase}
                                {#each problem.params as p}
                                    {#if submissionFailure.testCase[p.name] !== undefined}
                                        <div class="kv-row">
                                            <div>{p.name}:</div>
                                            <pre
                                                class="result-output">{JSON.stringify(
                                                    submissionFailure.testCase[
                                                        p.name
                                                    ],
                                                )}</pre>
                                        </div>
                                    {/if}
                                {/each}
                            {:else}
                                <div class="kv-row">
                                    <span>No input details available.</span>
                                </div>
                            {/if}
                        </div>
                    </div>
                    <div class="outputs">
                        {#if submissionFailure.type === "tle"}
                            <div class="result-item">
                                <span class="result-title">Result</span>
                                <pre
                                    class="result-output error">Time Limit Exceeded</pre>
                            </div>
                        {:else}
                            <div class="result-item">
                                <span class="result-title">Your Answer</span>
                                {#if submissionFailure.error}
                                    <span class="result-status error">Error</span>
                                    {#if getCrashHint(submissionFailure.error)}
                                        <pre class="result-output hint">{getCrashHint(submissionFailure.error)}</pre>
                                    {/if}
                                    <pre
                                        class="result-output error">{submissionFailure.error}</pre>
                                {:else}
                                    <span class="result-status"></span>
                                    <pre
                                        class="result-output">{submissionFailure.yourAnswer}</pre>
                                {/if}
                            </div>
                            <div class="result-item">
                                <span class="result-title">Correct Answer</span>
                                <pre
                                    class="result-output">{submissionFailure.expectedAnswer}</pre>
                            </div>
                        {/if}
                    </div>
                    <div class="use-case-action">
                        <button class="btn use-case-btn" on:click={useFailedTestCase}>
                            Use Test Case
                        </button>
                    </div>
                {:else if activeSubmitRow}
                    <div class="inputs">
                        <div class="result-title">Inputs</div>
                        <div class="kv-list">
                            {#each problem.params as p}
                                {#if activeSubmitRow[p.name] !== undefined}
                                    <div class="kv-row">
                                        <div>{p.name}:</div>
                                        <pre class="result-output">{JSON.stringify(activeSubmitRow[p.name])}</pre>
                                    </div>
                                {/if}
                            {/each}
                        </div>
                    </div>
                    <div class="outputs">
                        <div class="result-item">
                            <span class="result-title">Your Answer</span>
                            {#if activeSubmitRow.error}
                                <span class="result-status error">Error</span>
                                {#if getCrashHint(activeSubmitRow.error)}
                                    <pre class="result-output hint">{getCrashHint(activeSubmitRow.error)}</pre>
                                {/if}
                                <pre class="result-output error">{activeSubmitRow.error}</pre>
                            {:else}
                                <span class="result-status"></span>
                                <pre class="result-output">{activeSubmitRow.output}</pre>
                            {/if}
                        </div>
                        <div class="result-item">
                            <span class="result-title">Expected</span>
                            <pre class="result-output">{activeSubmitRow.correctAnswer}</pre>
                        </div>
                    </div>
                    <div class="use-case-action">
                        <button class="btn use-case-btn" on:click={useFailedTestCase}>
                            Use Test Case
                        </button>
                    </div>
                {/if}
            </div>
        {:else if activeMainTab === "debugger" && debugState}
```

Notes: the old in-block summary line ("TLE/Failed on Test N") is deleted — the footer
`runningMessage` (`Time Limit Exceeded on Test N`, `Runtime Error on Test N`, `Compile Error`,
`Wrong Answer on Test N`) plus the new outer summary carry verdict context. `case-selector`,
`case-btn-wrap`, `output btn case-btn`, `correct/incorrect/active`, `submission-view`,
`summary`, `inputs`, `outputs`, `use-case-action` all reuse existing CSS.

2. Metrics gate (line 2071):
```svelte
{#if executionTimeMs != null || memoryKB != null}
```
(was `status === "accepted" && ...`; metrics now render for Runs too, since Task 9 assigns
them in `handleRun`)

**Verify after Tasks 9-11:** `npx vitest run` + `npm run check` (no new errors).

---

## Task 12 — Offline images: bake `time`, switch defaults, README

**File: `docker/cpp/Dockerfile`** — extend the existing apt line:
```dockerfile
FROM gcc:13

RUN apt-get update -qq && apt-get install -y -qq gdb time && \
    rm -rf /var/lib/apt/lists/*
```

**New file: `docker/python/Dockerfile`:**
```dockerfile
# Cojudge Python Runner Image
#
# WHY: python:3.11-slim lacks /usr/bin/time (runtime/memory metrics) and offline
# machines cannot apt-get install it at run time.

FROM python:3.11-slim

RUN apt-get update -qq && apt-get install -y -qq time && \
    rm -rf /var/lib/apt/lists/*
```

**File: `src/lib/utils/cppUtil.ts`** line 4:
```ts
export const cppImage = env.COJUDGE_CPP_IMAGE || 'cojudge/cpp:13';
```

**File: `src/lib/utils/pythonUtil.ts`** lines 1-3 (exact current content — note `pythonUtil`
does NOT import `env` yet, unlike `cppUtil`):
```ts
// old
import type { Param } from "./util";

export const pythonImage = 'python:3.11-slim';

// new
import type { Param } from "./util";
import { env } from '$env/dynamic/private';

export const pythonImage = env.COJUDGE_PYTHON_IMAGE || 'cojudge/python:3.11';
```

**Build + prove (network available in this env):**
```powershell
docker pull gcc:13
docker pull python:3.11-slim
docker pull alpine/java:22-jdk        # marker judging image (offline prep needs it too)
docker build -f docker/cpp/Dockerfile -t cojudge/cpp:13 docker/cpp
docker build -f docker/python/Dockerfile -t cojudge/python:3.11 docker/python
docker run --rm cojudge/cpp:13 /usr/bin/time -f mem=%M true
docker run --rm cojudge/python:3.11 /usr/bin/time -f mem=%M true
docker run --rm --network none cojudge/cpp:13 /usr/bin/time -f mem=%M true   # offline proof
```
(`ensureTimeInstalled` now no-ops: `command -v /usr/bin/time` succeeds; the apt path remains
as a fallback for custom images built without `time`. DebugRunner and `/api/image/status`
both consume the same constants, so they follow automatically.)

**File: `README.md`** — add a section after the line-96 Docker note:

```markdown
## Offline setup

Judging needs `/usr/bin/time` inside the runner images for runtime/memory metrics; stock
`gcc:13` / `python:3.11-slim` do not ship it and the runtime install requires internet.
Build the bundled images once while online so Run/Submit work fully offline:

```bash
docker pull gcc:13
docker pull python:3.11-slim
docker pull alpine/java:22-jdk
docker build -f docker/cpp/Dockerfile -t cojudge/cpp:13 docker/cpp
docker build -f docker/python/Dockerfile -t cojudge/python:3.11 docker/python
```

Override image names with `COJUDGE_CPP_IMAGE` / `COJUDGE_PYTHON_IMAGE` if you build them
differently. The in-app "pull image" button only works for Docker Hub images — locally built
images must be created with the commands above.
```

---

## Task 13 — Acceptance (R2 / R5 / R6 / R7 end-to-end)

**Automated gates (must pass):**
1. `npx vitest run` — baseline 175 + new util tests, all green.
2. `npm run check` — no errors beyond the recorded Phase-0 baseline.

**API-level fault injection** (start `npm run dev`, then PowerShell):

```powershell
# helper: POST /api/run then poll
$base = 'http://localhost:5173'   # confirm actual port from dev output
function Poll-Job($jobId) { do { $r = Invoke-RestMethod "$base/api/run?jobId=$jobId"; Start-Sleep 1 } until ($r.ready); $r }

# 1) language guard: java must 400
try { Invoke-RestMethod "$base/api/run" -Method Post -ContentType 'application/json' `
      -Body '{"problemId":"two-sum","language":"java","code":"class Solution{}","testCases":[]}' }
catch { $_.Exception.Response.StatusCode.value__ }    # expect 400

# 2) Compile Error: bad C++ on a problem (use two-sum), expect verdict "Compile Error"
# 3) Runtime Error: python code `raise Exception("x")` → verdict "Runtime Error"
# 4) Wrong Answer: python `return [-1,-1]`-style wrong solution submit → verdict "Wrong Answer"
# 5) Accepted: correct solution → verdict "Accepted"
# 6) metrics: run response executionTimeMs/memoryKB non-null
```

**Browser acceptance** (Playwright MCP or manual; open any problem, e.g. two-sum):
- [ ] Language select shows only **C++ / Python**; default is C++ even though stored pref is `java`.
- [ ] Run correct sample → "Sample Tests Passed"; runtime + memory chips visible (not gated on Accepted).
- [ ] Submit wrong solution → footer `Wrong Answer on Test N`; submission tab: summary
      `Wrong Answer · Passed X / Y`, colored case pills, detail shows Inputs / Your Answer /
      Expected, **Use Test Case** appends inputs to the testcase tab.
- [ ] Submit `raise Exception("x")` (python) → `Runtime Error on Test N` + error detail pane.
- [ ] Submit C++ syntax error → `Compile Error` + compiler error pane (no case pills).
- [ ] Submit python `while True: pass` → `Time Limit Exceeded on Test N` (30s wait) + TLE pane.
- [ ] Playground language select lacks Java/TS/C#/Rust/Go; plaintext/markdown still selectable.
- [ ] After selecting a `.java` file rename → run returns a clear "Language not supported" error
      (400 backstop), no judge attempt.
- [ ] Offline proof: `docker run --rm --network none cojudge/cpp:13 /usr/bin/time -f mem=%M true`.

**Checkpoint:** If any item fails, fix in-place and re-run the full automated gates before
reporting completion.

---

## Self-Review Record

- [x] Every task carries exact old→new code or an exact insertion snippet (no placeholders).
- [x] Task boundaries: each has one primary file concern; Tasks 6-7 and 9-11 grouped only where
      the same file must be touched twice (import lines), verified safe to sequence.
- [x] Verification command after every code task; final acceptance task with checkboxes.
- [x] Existing behaviors preserved: chunked submit loop, `passedTc` advance, gameMode dispatch,
      marker-catch fallback, `submissionFailure` CE/RE/TLE detail, clone-error semantics.
- [x] No git commits (repo is not a git repository) — gates are `npx vitest run` + `npm run check`.
- [x] Ceilings marked `ponytail:` or recorded in Design Decisions: batch-level RE attribution,
      setup-failure-as-CE, no row browsing when a failure pane is open, playground stale-language
      backstop, pull button vs locally built images.
- [x] `progressValidation.PROGRAMMING_LANGUAGES` untouched — importing stale `java` settings
      cannot throw; coercion happens at read sites only (Tasks 3, 5).
- [x] Round-9 baseline recorded: 2390/2390 runOk, 0 compileFail, 0 runFail, 0 wrong.
