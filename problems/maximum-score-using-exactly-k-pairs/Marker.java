import java.util.*;
class Marker {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        long[][][] dp = new long[n + 1][m + 1][k + 1];
        for (long[][] layer : dp) {
            for (long[] row : layer) {
                Arrays.fill(row, Long.MIN_VALUE / 2);
            }
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j][0] = 0;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int t = 1; t <= k; t++) {
                    dp[i][j][t] = Math.max(dp[i - 1][j][t], dp[i][j - 1][t]);
                    dp[i][j][t] = Math.max(dp[i][j][t], dp[i - 1][j - 1][t - 1] + (long) nums1[i - 1] * nums2[j - 1]);
                }
            }
        }
        return dp[n][m][k];
    }

    public boolean isCorrect(int[] nums1, int[] nums2, int k, long output) {
        long expected = maxScore(nums1, nums2, k);
        return expected == output;
    }
}