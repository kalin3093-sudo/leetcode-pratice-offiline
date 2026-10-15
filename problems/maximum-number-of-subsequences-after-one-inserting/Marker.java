import java.util.*;

class Marker {
    public long numOfSubsequences(String s) {
        int n = s.length();
        long[] preL = new long[n + 1];
        long[] preLC = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preL[i + 1] = preL[i] + (s.charAt(i) == 'L' ? 1 : 0);
            preLC[i + 1] = preLC[i] + (s.charAt(i) == 'C' ? preL[i] : 0);
        }
        long[] sufT = new long[n + 2];
        long[] sufCT = new long[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            sufT[i] = sufT[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
            sufCT[i] = sufCT[i + 1] + (s.charAt(i) == 'C' ? sufT[i + 1] : 0);
        }
        
        long base = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'C') {
                base += preL[i] * sufT[i + 1];
            }
        }
        
        long best = base;
        for (int i = 0; i <= n; i++) {
            best = Math.max(best, base + sufCT[i]);
            best = Math.max(best, base + preL[i] * sufT[i]);
            best = Math.max(best, base + preLC[i]);
        }
        return best;
    }

    public boolean isCorrect(String s, long output) {
        return output == numOfSubsequences(s);
    }
}