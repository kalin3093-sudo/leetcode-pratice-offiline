import java.util.*;
class Marker {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] ps = new long[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ps[i + 1][j + 1] = ps[i + 1][j] + ps[i][j + 1] - ps[i][j] + grid[i][j];
            }
        }
        long total = ps[m][n];
        
        for (int r = 0; r < m - 1; r++) {
            long top = ps[r + 1][n];
            long bottom = total - top;
            if (top == bottom) return true;
        }
        for (int c = 0; c < n - 1; c++) {
            long left = ps[m][c + 1];
            long right = total - left;
            if (left == right) return true;
        }
        return false;
    }

    public boolean isCorrect(int[][] grid, boolean output) {
        return canPartitionGrid(grid) == output;
    }
}