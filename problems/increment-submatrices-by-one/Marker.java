import java.util.*;
class Marker {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n][n + 1];
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1], r2 = q[2], c2 = q[3];
            for (int r = r1; r <= r2; r++) {
                diff[r][c1]++;
                if (c2 + 1 < n) diff[r][c2 + 1]--;
            }
        }
        int[][] res = new int[n][n];
        for (int r = 0; r < n; r++) {
            int cur = 0;
            for (int c = 0; c < n; c++) {
                cur += diff[r][c];
                res[r][c] = cur;
            }
        }
        return res;
    }
    public boolean isCorrect(int n, int[][] queries, int[][] output) {
        return Arrays.deepEquals(rangeAddQueries(n, queries), output);
    }
}