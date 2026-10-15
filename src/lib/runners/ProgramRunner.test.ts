import { describe, expect, it } from 'vitest';
import { ProgramRunner } from './ProgramRunner';

describe('ProgramRunner.crashMessage', () => {
    it('prefers non-empty stderr', () => {
        expect(ProgramRunner.crashMessage(139, 'boom', 'partial out')).toBe('boom');
    });
    it('falls through whitespace-only stderr to the signal map', () => {
        expect(ProgramRunner.crashMessage(139, '  \n', 'partial out')).toBe('Segmentation fault (SIGSEGV)');
    });
    it('maps common signal exit codes', () => {
        expect(ProgramRunner.crashMessage(134, '', '')).toBe('Program aborted (SIGABRT)');
        expect(ProgramRunner.crashMessage(136, '', '')).toBe('Floating point exception (SIGFPE)');
    });
    it('falls back to stdout, then a generic message', () => {
        expect(ProgramRunner.crashMessage(1, '', 'some output')).toBe('some output');
        expect(ProgramRunner.crashMessage(1, '', '')).toBe('Program exited with code 1');
    });
});
