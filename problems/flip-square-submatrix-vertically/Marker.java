import java.util.*;
class Marker {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) System.arraycopy(grid[i], 0, res[i], 0, n);
        for (int i = 0; i < k / 2; i++) {
            int r1 = x + i, r2 = x + k - 1 - i;
            for (int c = y; c < y + k; c++) {
                int tmp = res[r1][c];
                res[r1][c] = res[r2][c];
                res[r2][c] = tmp;
            }
        }
        return res;
    }
    public boolean isCorrect(int[][] grid, int x, int y, int k, int[][] output) {
        return Arrays.deepEquals(reverseSubmatrix(grid, x, y, k), output);
    }
}