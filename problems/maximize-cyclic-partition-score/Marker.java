import java.util.*;
class Marker {
    public long maximumScore(int[] nums, int k) {
        int n = nums.length;
        long INF = (long) 1e18;
        
        long[][] dp = new long[2 * k + 1][3];
        for (int i = 0; i <= 2 * k; i++) Arrays.fill(dp[i], -INF);
        dp[0][1] = 0; // balance = 0 shifted by +1
        
        for (int x : nums) {
            long[][] ndp = new long[2 * k + 1][3];
            for (int i = 0; i <= 2 * k; i++) Arrays.fill(ndp[i], -INF);
            
            for (int picks = 0; picks <= 2 * k; picks++) {
                for (int bal = 0; bal < 3; bal++) {
                    if (dp[picks][bal] == -INF) continue;
                    
                    ndp[picks][bal] = Math.max(ndp[picks][bal], dp[picks][bal]);
                    
                    if (picks + 1 <= 2 * k) {
                        // Take as max (+x)
                        int nbal = Math.min(2, bal + 1);
                        ndp[picks + 1][nbal] = Math.max(ndp[picks + 1][nbal], dp[picks][bal] + x);
                        
                        // Take as min (-x)
                        nbal = Math.max(0, bal - 1);
                        ndp[picks + 1][nbal] = Math.max(ndp[picks + 1][nbal], dp[picks][bal] - x);
                    }
                }
            }
            dp = ndp;
        }
        
        long ans = 0;
        for (int picks = 0; picks <= 2 * k; picks++) {
            ans = Math.max(ans, dp[picks][1]); // balance = 0
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        return maximumScore(nums, k) == output;
    }
}