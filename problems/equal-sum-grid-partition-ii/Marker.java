import java.util.*;
class Marker {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return false;
        
        long[][] ps = new long[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ps[i + 1][j + 1] = ps[i + 1][j] + ps[i][j + 1] - ps[i][j] + grid[i][j];
            }
        }
        long total = ps[m][n];
        
        if (m == 1) {
            long left = 0;
            for (int c = 0; c < n - 1; c++) {
                left += grid[0][c];
                long right = total - left;
                if (left == right) return true;
                if (left - right == grid[0][c + 1] && c + 1 == n - 1) return true;
                if (right - left == grid[0][c] && c == 0) return true;
            }
            return false;
        }
        if (n == 1) {
            long top = 0;
            for (int r = 0; r < m - 1; r++) {
                top += grid[r][0];
                long bottom = total - top;
                if (top == bottom) return true;
                if (top - bottom == grid[r + 1][0] && r + 1 == m - 1) return true;
                if (bottom - top == grid[r][0] && r == 0) return true;
            }
            return false;
        }
        
        for (int r = 0; r < m - 1; r++) {
            long top = ps[r + 1][n];
            long bottom = total - top;
            if (top == bottom) return true;
            long diff = Math.abs(top - bottom);
            if (top > bottom) {
                if (hasRemovable(grid, r + 1, m - 1, 0, n - 1, diff, true)) return true;
            } else {
                if (hasRemovable(grid, 0, r, 0, n - 1, diff, true)) return true;
            }
        }
        
        for (int c = 0; c < n - 1; c++) {
            long left = ps[m][c + 1];
            long right = total - left;
            if (left == right) return true;
            long diff = Math.abs(left - right);
            if (left > right) {
                if (hasRemovable(grid, 0, m - 1, 0, c, diff, false)) return true;
            } else {
                if (hasRemovable(grid, 0, m - 1, c + 1, n - 1, diff, false)) return true;
            }
        }
        
        return false;
    }
    
    private boolean hasRemovable(int[][] grid, int r1, int r2, int c1, int c2, long diff, boolean isHorizontal) {
        int rows = r2 - r1 + 1;
        int cols = c2 - c1 + 1;
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                if (grid[i][j] == diff) {
                    if (rows > 1 && cols > 1) return true;
                    if (rows == 1 && cols == 1) return false;
                    if (rows == 1) {
                        if (j == c1 || j == c2) return true;
                    }
                    if (cols == 1) {
                        if (i == r1 || i == r2) return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCorrect(int[][] grid, boolean output) {
        return canPartitionGrid(grid) == output;
    }
}