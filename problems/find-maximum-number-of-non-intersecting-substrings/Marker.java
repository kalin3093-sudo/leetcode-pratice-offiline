import java.util.*;
class Marker {
    public int maxSubstrings(String word) {
        int n = word.length();
        int[] first = new int[26];
        Arrays.fill(first, -1);
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int c = word.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            dp[i + 1] = dp[i];
            if (i - first[c] >= 3) {
                dp[i + 1] = Math.max(dp[i + 1], dp[first[c]] + 1);
            }
        }
        return dp[n];
    }

    public boolean isCorrect(String word, int output) {
        return maxSubstrings(word) == output;
    }
}