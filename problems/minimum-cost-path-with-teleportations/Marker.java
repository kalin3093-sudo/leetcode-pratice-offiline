import java.util.*;
class Marker {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        final int INF = 1_000_000_000;
        int[][][] dp = new int[k + 1][m][n];

        // Layer 0: only right/down moves
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[0][i][j] = INF;
            }
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i-1][j] + grid[i][j]);
                if (j > 0) dp[0][i][j] = Math.min(dp[0][i][j], dp[0][i][j-1] + grid[i][j]);
            }
        }

        // Cells sorted by grid value
        int[][] cells = new int[m * n][3];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[idx++] = new int[]{grid[i][j], i, j};
            }
        }
        Arrays.sort(cells, Comparator.comparingInt(a -> a[0]));

        for (int t = 1; t <= k; t++) {
            // Initialize with INF
            for (int i = 0; i < m; i++) Arrays.fill(dp[t][i], INF);

            // Teleportation: destination value must be <= source value;
            // sweep descending, absorbing each value-group before assigning it
            int minSoFar = INF;
            int p = cells.length - 1;
            while (p >= 0) {
                int v = cells[p][0], q = p;
                while (q >= 0 && cells[q][0] == v) {
                    minSoFar = Math.min(minSoFar, dp[t-1][cells[q][1]][cells[q][2]]);
                    q--;
                }
                for (int r = q + 1; r <= p; r++) {
                    dp[t][cells[r][1]][cells[r][2]] = Math.min(dp[t][cells[r][1]][cells[r][2]], minSoFar);
                }
                p = q;
            }

            // Normal moves: right/down sweep
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i-1][j] + grid[i][j]);
                    if (j > 0) dp[t][i][j] = Math.min(dp[t][i][j], dp[t][i][j-1] + grid[i][j]);
                }
            }
        }

        int ans = INF;
        for (int t = 0; t <= k; t++) {
            ans = Math.min(ans, dp[t][m-1][n-1]);
        }
        return ans == INF ? -1 : ans;
    }

    public boolean isCorrect(int[][] grid, int k, int output) {
        int expected = minCost(grid, k);
        return expected == output;
    }
}