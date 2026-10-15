import java.util.*;

class Marker {
    public int maxScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = Integer.MIN_VALUE;
        
        // Check all rows for max subarray sum of length >= 2
        for (int i = 0; i < m; i++) {
            int cur = grid[i][0];
            for (int j = 1; j < n; j++) {
                cur = Math.max(grid[i][j - 1] + grid[i][j], cur + grid[i][j]);
                ans = Math.max(ans, cur);
            }
        }
        
        // Check all columns for max subarray sum of length >= 2
        for (int j = 0; j < n; j++) {
            int cur = grid[0][j];
            for (int i = 1; i < m; i++) {
                cur = Math.max(grid[i - 1][j] + grid[i][j], cur + grid[i][j]);
                ans = Math.max(ans, cur);
            }
        }
        
        // Check single cells not on border
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                ans = Math.max(ans, grid[i][j]);
            }
        }
        
        return ans;
    }

    public boolean isCorrect(int[][] grid, int output) {
        return output == maxScore(grid);
    }
}