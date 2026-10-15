import java.util.*;

class Marker {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] compat = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean ok = true;
                for (int r = 0; r < m; r++) {
                    if (Math.abs(grid[r][j] - grid[r][i]) > limit) {
                        ok = false;
                        break;
                    }
                }
                compat[i][j] = ok;
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (compat[i][j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[j]);
        }
        return ans;
    }

    public boolean isCorrect(int[][] grid, int limit, int output) {
        return output == maxConsistentColumns(grid, limit);
    }
}