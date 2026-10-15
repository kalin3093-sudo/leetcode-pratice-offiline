import java.util.*;
class Marker {
    public int[][] cyclicShift(int n, int[][] grid, int[] rowShift, int[] colShift) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int c = (j - rowShift[i] + n) % n;
                int r = (i - colShift[c] + n) % n;
                res[r][c] = grid[i][j];
            }
        }
        return res;
    }

    public boolean isCorrect(int n, int[][] grid, int[] rowShift, int[] colShift, int[][] output) {
        int[][] expected = cyclicShift(n, grid, rowShift, colShift);
        if (output == null || output.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (!Arrays.equals(expected[i], output[i])) return false;
        }
        return true;
    }
}
