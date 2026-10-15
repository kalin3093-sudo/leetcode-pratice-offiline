import java.util.*;
class Marker {
    public int almostPalindromic(String s) {
        int n = s.length();
        int maxLen = 0;
        
        // Try all centers (both odd and even length palindromes)
        for (int center = 0; center < 2 * n - 1; center++) {
            int l = center / 2;
            int r = center / 2 + center % 2;
            // Expand with at most one skip allowed
            maxLen = Math.max(maxLen, expandWithSkip(s, l, r, false));
        }
        return maxLen;
    }
    
    private int expandWithSkip(String s, int l, int r, boolean skipUsed) {
        int n = s.length();
        int best = 0;
        while (l >= 0 && r < n) {
            if (s.charAt(l) == s.charAt(r)) {
                best = Math.max(best, r - l + 1);
                l--;
                r++;
            } else if (!skipUsed) {
                // Try skipping left or right
                int skipLeft = expandWithSkip(s, l - 1, r, true);
                int skipRight = expandWithSkip(s, l, r + 1, true);
                return Math.max(best, Math.max(skipLeft, skipRight));
            } else {
                break;
            }
        }
        // If we reached boundary without mismatch, we can still skip one boundary char
        if (!skipUsed) {
            // Can skip one more char on left or right if available
            if (l >= 0) best = Math.max(best, r - l); // skip s[l]
            if (r < n) best = Math.max(best, r - l); // skip s[r]
        }
        return best;
    }

    public boolean isCorrect(String s, int output) {
        return output == almostPalindromic(s);
    }
}