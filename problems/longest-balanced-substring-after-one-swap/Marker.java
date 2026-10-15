import java.util.*;
class Marker {
    public int longestBalanced(String s) {
        int n = s.length();
        int total0 = 0, total1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') total0++;
            else total1++;
        }
        int maxPossible = 2 * Math.min(total0, total1);
        if (maxPossible == 0) return 0;
        
        // Try to find substring of length maxPossible that can be balanced with at most one swap
        // Check all windows of length maxPossible
        int cnt0 = 0, cnt1 = 0;
        for (int i = 0; i < maxPossible; i++) {
            if (s.charAt(i) == '0') cnt0++; else cnt1++;
        }
        if (canBalance(cnt0, cnt1, total0, total1)) return maxPossible;
        for (int i = maxPossible; i < n; i++) {
            if (s.charAt(i - maxPossible) == '0') cnt0--; else cnt1--;
            if (s.charAt(i) == '0') cnt0++; else cnt1++;
            if (canBalance(cnt0, cnt1, total0, total1)) return maxPossible;
        }
        
        // Try smaller even lengths
        for (int len = maxPossible - 2; len >= 2; len -= 2) {
            cnt0 = 0; cnt1 = 0;
            for (int i = 0; i < len; i++) {
                if (s.charAt(i) == '0') cnt0++; else cnt1++;
            }
            if (canBalance(cnt0, cnt1, total0, total1)) return len;
            for (int i = len; i < n; i++) {
                if (s.charAt(i - len) == '0') cnt0--; else cnt1--;
                if (s.charAt(i) == '0') cnt0++; else cnt1++;
                if (canBalance(cnt0, cnt1, total0, total1)) return len;
            }
        }
        return 0;
    }
    
    // Can we make this window balanced with at most one swap?
    // cnt0, cnt1: counts in window
    // total0, total1: total counts in string
    private boolean canBalance(int cnt0, int cnt1, int total0, int total1) {
        int diff = cnt1 - cnt0;
        if (diff == 0) return true; // already balanced
        if (diff == 2) {
            // Need to swap a 1 inside with a 0 outside
            // Need at least one 0 outside: total0 > cnt0
            return total0 > cnt0;
        }
        if (diff == -2) {
            // Need to swap a 0 inside with a 1 outside
            return total1 > cnt1;
        }
        return false;
    }

    public boolean isCorrect(String s, int output) {
        return output == longestBalanced(s);
    }
}