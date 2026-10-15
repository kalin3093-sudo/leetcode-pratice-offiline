import java.util.*;
class Marker {
    public long minArraySum(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 4);
        dp[0] = 0;
        
        Map<Integer, Long> best = new HashMap<>();
        best.put(0, 0L); // remainder -> min dp[j]
        
        for (int i = 1; i <= n; i++) {
            int rem = (int) (prefix[i] % k);
            if (rem < 0) rem += k;
            
            // Option 1: don't delete a subarray ending at i-1
            dp[i] = dp[i - 1] + nums[i - 1];
            
            // Option 2: delete a subarray ending at i-1 with sum divisible by k
            if (best.containsKey(rem)) {
                dp[i] = Math.min(dp[i], best.get(rem));
            }
            
            // Update best for this remainder
            best.put(rem, Math.min(best.getOrDefault(rem, Long.MAX_VALUE), dp[i]));
        }
        
        return dp[n] % 1000000007;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return minArraySum(nums, k) == output;
    }
}