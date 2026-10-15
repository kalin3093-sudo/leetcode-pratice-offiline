import java.util.*;
class Marker {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        dp[0] = 0;
        for (int j = 1; j <= n; j++) {
            long best = Long.MAX_VALUE;
            for (int d = 1; d <= 3 && j - d >= 0; d++) {
                best = Math.min(best, dp[j - d] + (long) d * d);
            }
            dp[j] = costs[j - 1] + best;
        }
        return (int) dp[n];
    }

    public boolean isCorrect(int n, int[] costs, int output) {
        return climbStairs(n, costs) == output;
    }
}
