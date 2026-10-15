import java.util.*;
class Marker {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // dp[i][j] = set of possible XOR values reaching (i,j)
        // Since grid values <= 1023, XOR values <= 1023
        // We can use bitset or boolean array
        boolean[][][] dp = new boolean[m][n][1024];
        
        dp[0][0][grid[0][0]] = true;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int val = grid[i][j];
                if (i > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[i-1][j][x]) {
                            dp[i][j][x ^ val] = true;
                        }
                    }
                }
                if (j > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[i][j-1][x]) {
                            dp[i][j][x ^ val] = true;
                        }
                    }
                }
            }
        }
        
        for (int x = 0; x < 1024; x++) {
            if (dp[m-1][n-1][x]) return x;
        }
        return -1;
    }

    public boolean isCorrect(int[][] grid, int output) {
        return minCost(grid) == output;
    }
}