import java.util.*;
class Marker {
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 4);
        }
        dp[0][0] = 0;
        
        // Divide and conquer optimization
        for (int parts = 1; parts <= k; parts++) {
            compute(parts, parts, n, parts - 1, n - 1, dp, prefix);
        }
        
        return dp[k][n];
    }
    
    private void compute(int parts, int l, int r, int optL, int optR, long[][] dp, long[] prefix) {
        if (l > r) return;
        int mid = (l + r) / 2;
        long bestVal = Long.MAX_VALUE / 4;
        int bestK = -1;
        
        int start = Math.max(optL, parts - 1);
        int end = Math.min(optR, mid - 1);
        
        for (int j = start; j <= end; j++) {
            long sum = prefix[mid] - prefix[j];
            long cost = sum * (sum + 1) / 2;
            long val = dp[parts - 1][j] + cost;
            if (val < bestVal) {
                bestVal = val;
                bestK = j;
            }
        }
        
        dp[parts][mid] = bestVal;
        compute(parts, l, mid - 1, optL, bestK, dp, prefix);
        compute(parts, mid + 1, r, bestK, optR, dp, prefix);
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return minPartitionScore(nums, k) == output;
    }
}