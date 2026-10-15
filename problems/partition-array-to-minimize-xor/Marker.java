import java.util.*;
class Marker {
    public int minXor(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] ^ nums[i];
        int INF = Integer.MAX_VALUE;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                for (int t = j - 1; t < i; t++) {
                    if (dp[t][j - 1] == INF) continue;
                    int cand = Math.max(dp[t][j - 1], pre[i] ^ pre[t]);
                    if (cand < dp[i][j]) dp[i][j] = cand;
                }
            }
        }
        return dp[n][k];
    }
    public boolean isCorrect(int[] nums, int k, int output) {
        return output == minXor(nums, k);
    }
}
