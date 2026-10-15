import java.util.*;
class Marker {
    public int minOperations(String word1, String word2) {
        int n = word1.length();
        // dp[i][j] = min operations to transform word1[i:] to word2[j:]
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], n + 1);
        }
        dp[n][n] = 0;
        
        // We process from right to left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Option 1: use a single substring starting at i
                // Try all lengths len
                for (int len = 1; i + len <= n && j + len <= n; len++) {
                    String s1 = word1.substring(i, i + len);
                    String s2 = word2.substring(j, j + len);
                    int cost = minOpsForSubstring(s1, s2);
                    dp[i][j] = Math.min(dp[i][j], cost + dp[i + len][j + len]);
                }
            }
        }
        return dp[0][0];
    }
    
    // Min operations to transform s1 to s2 using the three operations on the whole substring
    private int minOpsForSubstring(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        int len = s1.length();
        int best = len; // worst case: replace all chars
        
        // Try reverse operation
        String rev = new StringBuilder(s1).reverse().toString();
        if (rev.equals(s2)) return 1;
        
        // Try swap operations
        // Count mismatches
        int mismatches = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) mismatches++;
        }
        
        // Try single swap
        if (mismatches == 2) {
            int i1 = -1, i2 = -1;
            for (int i = 0; i < len; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    if (i1 == -1) i1 = i;
                    else i2 = i;
                }
            }
            if (s1.charAt(i1) == s2.charAt(i2) && s1.charAt(i2) == s2.charAt(i1)) {
                best = Math.min(best, 1);
            }
        }
        
        // Try reverse + swap combo
        int revMismatches = 0;
        for (int i = 0; i < len; i++) {
            if (rev.charAt(i) != s2.charAt(i)) revMismatches++;
        }
        if (revMismatches == 2) {
            int i1 = -1, i2 = -1;
            for (int i = 0; i < len; i++) {
                if (rev.charAt(i) != s2.charAt(i)) {
                    if (i1 == -1) i1 = i;
                    else i2 = i;
                }
            }
            if (rev.charAt(i1) == s2.charAt(i2) && rev.charAt(i2) == s2.charAt(i1)) {
                best = Math.min(best, 2);
            }
        }
        
        // Try replace operations
        best = Math.min(best, mismatches);
        best = Math.min(best, 1 + revMismatches); // reverse then replace
        
        return best;
    }

    public boolean isCorrect(String word1, String word2, int output) {
        return minOperations(word1, word2) == output;
    }
}