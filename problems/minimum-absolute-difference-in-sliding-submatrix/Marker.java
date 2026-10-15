import java.util.*;
class Marker {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        for (int i = 0; i + k <= m; i++) {
            for (int j = 0; j + k <= n; j++) {
                int[] vals = new int[k * k];
                int idx = 0;
                for (int x = i; x < i + k; x++)
                    for (int y = j; y < j + k; y++)
                        vals[idx++] = grid[x][y];
                Arrays.sort(vals);
                int best = Integer.MAX_VALUE;
                for (int t = 1; t < vals.length; t++) {
                    if (vals[t] != vals[t - 1]) best = Math.min(best, vals[t] - vals[t - 1]);
                }
                ans[i][j] = best == Integer.MAX_VALUE ? 0 : best;
            }
        }
        return ans;
    }

    public boolean isCorrect(int[][] grid, int k, int[][] output) {
        if (output == null) return false;
        return Arrays.deepEquals(minAbsDiff(grid, k), output);
    }
}
