import java.util.*;

class Marker {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        if (k == 0) return 0;
        int maxPeaks = n / 2;
        if (k > maxPeaks) return -1;
        
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            int prev = nums[(i - 1 + n) % n];
            int next = nums[(i + 1) % n];
            cost[i] = Math.max(0, Math.max(prev, next) + 1 - nums[i]);
        }
        
        int ans = Integer.MAX_VALUE;
        
        // Case 1: index 0 is not a peak
        // Solve linear for indices 1..n-1 (length n-1)
        ans = Math.min(ans, solveLinear(cost, 1, n - 1, k));
        
        // Case 2: index 0 is a peak
        if (k > 0) {
            // indices 1 and n-1 cannot be peaks
            // Solve linear for indices 2..n-2 (length n-3)
            int sub = solveLinear(cost, 2, n - 2, k - 1);
            if (sub != Integer.MAX_VALUE) {
                ans = Math.min(ans, sub + cost[0]);
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    private int solveLinear(int[] cost, int start, int end, int k) {
        // Solve for indices [start, end] inclusive, length = end - start + 1
        // k peaks to select
        int len = end - start + 1;
        if (len <= 0) return k == 0 ? 0 : Integer.MAX_VALUE;
        if (k > (len + 1) / 2) return Integer.MAX_VALUE;
        
        int[][] dp0 = new int[len + 1][k + 1];
        int[][] dp1 = new int[len + 1][k + 1];
        for (int i = 0; i <= len; i++) {
            Arrays.fill(dp0[i], Integer.MAX_VALUE);
            Arrays.fill(dp1[i], Integer.MAX_VALUE);
        }
        dp0[0][0] = 0;
        
        for (int i = 1; i <= len; i++) {
            int idx = start + i - 1;
            for (int j = 0; j <= k; j++) {
                // not peak
                dp0[i][j] = Math.min(dp0[i - 1][j], dp1[i - 1][j]);
                // peak
                if (j > 0 && dp0[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp1[i][j] = dp0[i - 1][j - 1] + cost[idx];
                }
            }
        }
        return Math.min(dp0[len][k], dp1[len][k]);
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return minOperations(nums, k) == output;
    }
}