import java.util.*;

public class Marker {

    public int countCells(char[][] grid, String pattern) {
        int m = grid.length, n = grid[0].length;
        int total = m * n;
        char[] h = new char[total];
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                h[r * n + c] = grid[r][c];
        char[] v = new char[total];
        for (int c = 0; c < n; c++)
            for (int r = 0; r < m; r++)
                v[c * m + r] = grid[r][c];
        boolean[] hor = new boolean[total];
        mark(h, pattern, hor);
        boolean[] vtmp = new boolean[total];
        mark(v, pattern, vtmp);
        boolean[] ver = new boolean[total];
        for (int j = 0; j < total; j++) {
            if (vtmp[j]) {
                int r = j % m, c = j / m;
                ver[r * n + c] = true;
            }
        }
        int ans = 0;
        for (int i = 0; i < total; i++) if (hor[i] && ver[i]) ans++;
        return ans;
    }

    void mark(char[] text, String pat, boolean[] cover) {
        int P = pat.length();
        if (P == 0 || P > text.length) return;
        int[] pi = new int[P];
        for (int i = 1, j = 0; i < P; i++) {
            while (j > 0 && pat.charAt(i) != pat.charAt(j)) j = pi[j - 1];
            if (pat.charAt(i) == pat.charAt(j)) j++;
            pi[i] = j;
        }
        for (int i = 0, j = 0; i < text.length; i++) {
            while (j > 0 && text[i] != pat.charAt(j)) j = pi[j - 1];
            if (text[i] == pat.charAt(j)) j++;
            if (j == P) {
                for (int t = i - P + 1; t <= i; t++) cover[t] = true;
                j = pi[j - 1];
            }
        }
    }

    public boolean isCorrect(char[][] grid, String pattern, int out) {
        return countCells(grid, pattern) == out;
    }
}
