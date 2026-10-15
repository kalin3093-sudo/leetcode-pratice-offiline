import java.util.*;
class Marker {
    public char processStr(String s, long k) {
        int m = s.length();
        long[] len = new long[m];
        long prev = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            if (c == '*') prev = Math.max(0, prev - 1);
            else if (c == '#') prev = prev * 2;
            else if (c == '%') {}
            else prev = prev + 1;
            len[i] = prev;
        }
        if (k < 0 || k >= prev) return '.';
        for (int i = m - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long before = i == 0 ? 0 : len[i - 1];
            if (c != '*' && c != '#' && c != '%') {
                if (k == before) return c;
            } else if (c == '#') {
                if (k >= before) k -= before;
            } else if (c == '%') {
                k = len[i] - 1 - k;
            }
        }
        return '.';
    }

    public boolean isCorrect(String s, long k, char output) {
        return processStr(s, k) == output;
    }
}
