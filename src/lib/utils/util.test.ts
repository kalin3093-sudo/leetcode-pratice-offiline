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
