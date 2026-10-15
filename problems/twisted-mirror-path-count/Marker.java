import java.util.*;

class Marker {
    private int[][][] go;
    private int[][] grid;
    private int m, n;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        go = new int[m][n][2];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(go[i][j], -2);
        final long MOD = 1000000007L;
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;
                if (i == m - 1 && j == n - 1) continue;
                int tr = land(i, j + 1, 0);
                if (tr >= 0) {
                    int r = tr / n, c = tr % n;
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
                int td = land(i + 1, j, 1);
                if (td >= 0) {
                    int r = td / n, c = td % n;
                    dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                }
            }
        }
        return (int) dp[m - 1][n - 1];
    }

    private int land(int r, int c, int dir) {
        if (r < 0 || r >= m || c < 0 || c >= n) return -1;
        if (go[r][c][dir] != -2) return go[r][c][dir];
        int res;
        if (grid[r][c] == 0) res = r * n + c;
        else if (dir == 0) res = land(r + 1, c, 1);
        else res = land(r, c + 1, 0);
        go[r][c][dir] = res;
        return res;
    }

    public boolean isCorrect(int[][] grid, int output) {
        return output == uniquePaths(grid);
    }
}
