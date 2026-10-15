import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int numberOfRoutes(String[] grid, int d) {
        int n = grid.length;
        int m = grid[0].length();
        boolean[][] blocked = new boolean[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                blocked[r][c] = grid[r].charAt(c) == '#';
            }
        }

        int maxHoriz = d;
        int maxVert = (int) Math.sqrt(1L * d * d - 1);

        int[][] dp0 = new int[n][m];
        int[][] dp1 = new int[n][m];

        for (int c = 0; c < m; c++) {
            if (!blocked[n - 1][c]) dp0[n - 1][c] = 1;
        }
        // Compute dp1 for bottom row (horizontal moves within bottom row)
        int[] pref0bot = new int[m + 1];
        for (int c = 0; c < m; c++) {
            pref0bot[c + 1] = (pref0bot[c] + dp0[n - 1][c]) % MOD;
        }
        for (int c = 0; c < m; c++) {
            if (blocked[n - 1][c]) continue;
            int L = Math.max(0, c - maxHoriz);
            int R = Math.min(m - 1, c + maxHoriz);
            dp1[n - 1][c] = (pref0bot[R + 1] - pref0bot[L] - dp0[n - 1][c] + 2 * MOD) % MOD;
        }

        for (int r = n - 2; r >= 0; r--) {
            int[] pref0 = new int[m + 1];
            int[] pref1 = new int[m + 1];
            for (int c = 0; c < m; c++) {
                pref0[c + 1] = (pref0[c] + dp0[r + 1][c]) % MOD;
                pref1[c + 1] = (pref1[c] + dp1[r + 1][c]) % MOD;
            }

            for (int c = 0; c < m; c++) {
                if (blocked[r][c]) continue;
                int L = Math.max(0, c - maxVert);
                int R = Math.min(m - 1, c + maxVert);
                int sum = (pref0[R + 1] - pref0[L] + MOD) % MOD;
                sum = (sum + pref1[R + 1] - pref1[L] + MOD) % MOD;
                dp0[r][c] = sum;
            }

            int[] pref0cur = new int[m + 1];
            for (int c = 0; c < m; c++) {
                pref0cur[c + 1] = (pref0cur[c] + dp0[r][c]) % MOD;
            }

            for (int c = 0; c < m; c++) {
                if (blocked[r][c]) continue;
                int L = Math.max(0, c - maxHoriz);
                int R = Math.min(m - 1, c + maxHoriz);
                dp1[r][c] = (pref0cur[R + 1] - pref0cur[L] - dp0[r][c] + 2 * MOD) % MOD;
            }
        }

        int ans = 0;
        for (int c = 0; c < m; c++) {
            ans = (ans + dp0[0][c]) % MOD;
            ans = (ans + dp1[0][c]) % MOD;
        }
        return ans;
    }

    public boolean isCorrect(String[] grid, int d, int output) {
        return output == numberOfRoutes(grid, d);
    }
}