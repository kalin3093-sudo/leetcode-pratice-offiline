import java.util.*;
class Marker {
    public String[] createGrid(int m, int n, int k) {
        List<char[][]> cands = new ArrayList<>();
        if (k == 1) {
            cands.add(corridor(m, n));
        } else if (k == 2) {
            if (m >= 2 && n >= 2) cands.add(g2a(m, n));
            if (m >= 2 && n >= 2) cands.add(g2b(m, n));
        } else if (k == 3) {
            if (m >= 2 && n >= 3) cands.add(g3a(m, n));
            if (m >= 3 && n >= 2) cands.add(g3b(m, n));
        } else if (k == 4) {
            if (m >= 3 && n >= 3) cands.add(g4(m, n));
            if (m >= 2 && n >= 4) cands.add(g4a(m, n));
            if (m >= 4 && n >= 2) cands.add(g4b(m, n));
        }
        for (char[][] g : cands) {
            if (pathCount(g) == k) return toStrings(g);
        }
        return new String[0];
    }

    private char[][] empty(int m, int n) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(g[i], '#');
        return g;
    }

    // single path: right along row 0 to col n-2, down one, right one, then down the last column
    private char[][] corridor(int m, int n) {
        char[][] g = empty(m, n);
        if (n == 1) {
            for (int i = 0; i < m; i++) g[i][0] = '.';
            return g;
        }
        if (m == 1) {
            for (int j = 0; j < n; j++) g[0][j] = '.';
            return g;
        }
        for (int j = 0; j <= n - 2; j++) g[0][j] = '.';
        g[1][n - 2] = '.';
        g[1][n - 1] = '.';
        for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // row 0 open, row 1 cols 1..n-1 open, below only last column
    private char[][] g2a(int m, int n) {
        char[][] g = empty(m, n);
        for (int j = 0; j < n; j++) g[0][j] = '.';
        for (int j = 1; j < n; j++) g[1][j] = '.';
        for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // 2x2 open block at top-left plus corridor along row 1 then last column
    private char[][] g2b(int m, int n) {
        char[][] g = empty(m, n);
        g[0][0] = g[0][1] = g[1][0] = g[1][1] = '.';
        for (int j = 2; j < n; j++) g[1][j] = '.';
        for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // 2x3 open block plus corridor
    private char[][] g3a(int m, int n) {
        char[][] g = empty(m, n);
        for (int i = 0; i < 2; i++) for (int j = 0; j < 3; j++) g[i][j] = '.';
        for (int j = 3; j < n; j++) g[1][j] = '.';
        for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // 3x2 open block plus corridor
    private char[][] g3b(int m, int n) {
        char[][] g = empty(m, n);
        for (int i = 0; i < 3; i++) for (int j = 0; j < 2; j++) g[i][j] = '.';
        for (int i = 3; i < m; i++) g[i][1] = '.';
        for (int j = 2; j < n; j++) g[m - 1][j] = '.';
        return g;
    }

    // 3x3 block with top-right and bottom-left blocked plus corridor
    private char[][] g4(int m, int n) {
        char[][] g = empty(m, n);
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) g[i][j] = '.';
        g[0][2] = '#';
        g[2][0] = '#';
        for (int j = 3; j < n; j++) g[2][j] = '.';
        for (int i = 3; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // 2x4 open block plus corridor
    private char[][] g4a(int m, int n) {
        char[][] g = empty(m, n);
        for (int i = 0; i < 2; i++) for (int j = 0; j < 4; j++) g[i][j] = '.';
        for (int j = 4; j < n; j++) g[1][j] = '.';
        for (int i = 2; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    // 4x2 open block plus corridor
    private char[][] g4b(int m, int n) {
        char[][] g = empty(m, n);
        for (int i = 0; i < 4; i++) for (int j = 0; j < 2; j++) g[i][j] = '.';
        for (int i = 4; i < m; i++) g[i][1] = '.';
        for (int j = 2; j < n; j++) g[m - 1][j] = '.';
        return g;
    }

    private String[] toStrings(char[][] g) {
        String[] res = new String[g.length];
        for (int i = 0; i < g.length; i++) res[i] = new String(g[i]);
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

    public boolean isCorrect(int m, int n, int k, String[] output) {
        String[] ref = createGrid(m, n, k);
        if (ref.length == 0) return output == null || output.length == 0;
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
        return pathCount(g) == k;
    }
}
