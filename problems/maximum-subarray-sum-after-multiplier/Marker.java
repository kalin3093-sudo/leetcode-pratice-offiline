import java.util.*;
class Marker {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] dp0 = new long[n];
        long[] dp1 = new long[n];
        long[] dp2 = new long[n];
        long[] dp3 = new long[n];
        
        long ans = Long.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            long mul = (long) x * k;
            long div = x / k;  // Java's / truncates toward 0: floor for positive, ceiling for negative
            
            if (i == 0) {
                dp0[i] = x;
                dp1[i] = mul;
                dp2[i] = div;
                dp3[i] = x;
            } else {
                dp0[i] = Math.max(dp0[i - 1] + x, x);
                dp1[i] = Math.max(Math.max(dp0[i - 1], 0) + mul, Math.max(dp1[i - 1] + mul, mul));
                dp2[i] = Math.max(Math.max(dp0[i - 1], 0) + div, Math.max(dp2[i - 1] + div, div));
                dp3[i] = Math.max(Math.max(Math.max(dp1[i - 1], dp2[i - 1]), 0) + x, x);
            }
            
            ans = Math.max(ans, Math.max(Math.max(dp0[i], dp1[i]), Math.max(dp2[i], dp3[i])));
        }
        
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxSubarraySum(nums, k);
        return expected == output;
    }
}