import java.util.*;
class Marker {
    public int minTravelTime(int l, int n, int k, int[] position, int[] time) {
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + time[i];
        final int INF = Integer.MAX_VALUE / 4;
        // dp[i][j][s]: sign i is kept, j deletions so far, s consecutive deletions right before i
        int[][][] dp = new int[n][k + 1][n + 1];
        for (int[][] a : dp) for (int[] b : a) Arrays.fill(b, INF);
        dp[0][0][0] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= k; j++) {
                for (int s = 0; s <= i; s++) {
                    if (dp[i][j][s] == INF) continue;
                    // sign i governs segment i -> q; absorbed times of the s deleted signs before i
                    int rate = time[i] + pre[i] - pre[i - s];
                    for (int q = i + 1; q < n; q++) {
                        int nd = q - i - 1;
                        if (j + nd > k) break;
                        int cost = dp[i][j][s] + (position[q] - position[i]) * rate;
                        if (cost < dp[q][j + nd][nd]) dp[q][j + nd][nd] = cost;
                    }
                }
            }
        }
        int ans = INF;
        for (int s = 0; s <= n; s++) ans = Math.min(ans, dp[n - 1][k][s]);
        return ans;
    }

    public boolean isCorrect(int l, int n, int k, int[] position, int[] time, int output) {
        return minTravelTime(l, n, k, position, time) == output;
    }
}
