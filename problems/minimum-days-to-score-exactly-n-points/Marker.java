import java.util.*;
class Marker {
    public int minDays(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // Max streak length L such that L*(L+1)/2 <= n
        int maxL = (int)Math.sqrt(2 * n) + 2;
        while (maxL * (maxL + 1) / 2 > n) maxL--;

        for (int L = 1; L <= maxL; L++) {
            int score = L * (L + 1) / 2;
            int cost = L + 1; // streak days + skip day
            for (int s = score; s <= n; s++) {
                if (dp[s - score] != Integer.MAX_VALUE) {
                    dp[s] = Math.min(dp[s], dp[s - score] + cost);
                }
            }
        }
        return dp[n] - 1; // no skip needed after last streak
    }

    public boolean isCorrect(int n, int output) {
        int expected = minDays(n);
        return expected == output;
    }
}