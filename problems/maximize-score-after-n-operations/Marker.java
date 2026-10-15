import java.util.*;
class Marker {
    public int maxScore(int[] nums) {
        int n = nums.length;
        int m = n / 2;
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = gcd(j, i, nums[i], nums[j]);
            }
        }
        
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == -1) continue;
            int pairs = Integer.bitCount(mask) / 2;
            if (pairs == m) continue;
            int op = pairs + 1;
            
            int first = -1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    if (first == -1) first = i;
                    else {
                        int nmask = mask | (1 << first) | (1 << i);
                        dp[nmask] = Math.max(dp[nmask], dp[mask] + op * gcd[first][i]);
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
    
    private int gcd(int a, int b, int x, int y) {
        while (y != 0) {
            int t = x % y;
            x = y;
            y = t;
        }
        return x;
    }

    public boolean isCorrect(int[] nums, int output) {
        return maxScore(nums) == output;
    }
}