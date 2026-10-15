import java.util.*;
class Marker {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(g[i], '#');
        if (n == 1) {
            for (int i = 0; i < m; i++) g[i][0] = '.';
        } else if (m == 1) {
            for (int j = 0; j < n; j++) g[0][j] = '.';
        } else {
            for (int j = 0; j <= n - 2; j++) g[0][j] = '.';
            g[1][n - 2] = '.';
            g[1][n - 1] = '.';
            for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        }
        String[] res = new String[m];
        for (int i = 0; i < m; i++) res[i] = new String(g[i]);
        return res;
    }

    private long pathCount(char[][] g) {
        int m = g.length, n = g[0].length;
        if (g[0][0] == '#' || g[m - 1][n - 1] == '#') return 0;
        long[] dp = new long[n];
        for (int i = 0; i < m; i++) {
            long[] nd = new long[n];
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '#') continue;
                if (i == 0 && j == 0) { nd[j] = 1; continue; }
                long v = 0;
                if (j > 0) v += nd[j - 1];
                if (i > 0) v += dp[j];
                nd[j] = v;
            }
            dp = nd;
        }
        return dp[n - 1];
    }

    public boolean isCorrect(int m, int n, String[] output) {
        if (output == null || output.length != m) return false;
        char[][] g = new char[m][];
        for (int i = 0; i < m; i++) {
            if (output[i] == null || output[i].length() != n) return false;
            for (int t = 0; t < n; t++) {
                char c = output[i].charAt(t);
                if (c != '.' && c != '#') return false;
            }
            g[i] = output[i].toCharArray();
        }
        if (g[0][0] != '.' || g[m - 1][n - 1] != '.') return false;
        return pathCount(g) == 1;
    }
}
