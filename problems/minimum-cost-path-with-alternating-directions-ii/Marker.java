import java.util.*;
class Marker {
    public long minCost(int m, int n, int[][] waitCost) {
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + waitCost[i][0] + (long)(i+1) * 1;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + waitCost[0][j] + (long)1 * (j+1);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + waitCost[i][j] + (long)(i+1) * (j+1);
            }
        }
        return dp[m-1][n-1] - waitCost[m-1][n-1];
    }

    public boolean isCorrect(int m, int n, int[][] waitCost, long output) {
        long expected = minCost(m, n, waitCost);
        return expected == output;
    }
}