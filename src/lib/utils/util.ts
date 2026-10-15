import type Dockerode from "dockerode";

export type JobStatus = 'pending' | 'preparing' | 'running' | 'completed' | 'error' | 'judging';

export function getDifficultyClass(difficulty: string) {
    if (!difficulty) return '';
    return `difficulty-${difficulty.toLowerCase()}`;
}

export type Param = {
    name: string;
    type: string;
};

export type ProgrammingLanguage = 'java' | 'python' | 'cpp' | 'csharp' | 'rust' | 'go' | 'typescript' | 'plaintext' | 'markdown';

export const DEBUG_SUPPORTED_LANGUAGES: ProgrammingLanguage[] = ['python', 'java', 'cpp', 'go', 'csharp', 'rust', 'typescript'];

export function isDebugSupported(language: string): boolean {
    return DEBUG_SUPPORTED_LANGUAGES.includes(language as ProgrammingLanguage);
}

export const LINUX_TIMEOUT_CODE = 124;

export const EXECUTION_TIMEOUT_SECONDS = '30';

export const TIMEOUT_MESSAGE = 'TIMEOUT';

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

export function extractOperations(testCases: any[], className: string): string[] {
    const opSet = new Set<string>();
    for (const tc of testCases) {
        const opsField = Object.keys(tc).find(k => k === 'operations' || k.endsWith('_operations'));
        if (!opsField) continue;
        try {
            const ops = JSON.parse(tc[opsField]);
            for (const op of ops) {
                if (op !== className) opSet.add(op);
            }
        } catch {}
    }
    return Array.from(opSet);
}

const availableImages = new Set<string>();
let executionShutdown = false;

export function beginExecutionShutdown() {
    executionShutdown = true;
}

function assertExecutionActive() {
    if (executionShutdown) throw new Error('Server is shutting down');
}

export function resetImageCache() {
    availableImages.clear();
}

export async function ensureImageAvailable(docker: Dockerode, image: string) {
    assertExecutionActive();
    if (availableImages.has(image)) return;
    try {
        await docker.getImage(image).inspect();
        assertExecutionActive();
        availableImages.add(image);
    } catch (err: any) {
        const notFound = err?.statusCode === 404 || /no such image/i.test(String(err?.message ?? ''));
        if (!notFound) throw err;
        await new Promise<void>((resolve, reject) => {
            docker.pull(image, (pullErr: any, stream: any) => {
                if (pullErr) return reject(pullErr);
                (docker as any).modem.followProgress(
                    stream,
                    (doneErr: any) => (doneErr ? reject(doneErr) : resolve()),
                    () => {}
                );
            });
        });
        assertExecutionActive();
        availableImages.add(image);
    }
}
