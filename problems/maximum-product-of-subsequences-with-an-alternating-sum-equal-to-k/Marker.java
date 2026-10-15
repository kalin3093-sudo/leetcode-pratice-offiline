import java.util.*;

class Marker {
    public int maxProduct(int[] nums, int k, int limit) {
        int n = nums.length;
        int offset = 2000;
        int maxSum = 4000;
        // dp[sum][parity][product] = achievable
        boolean[][][] dp = new boolean[maxSum + 1][2][limit + 1];
        dp[offset][0][1] = true; // empty subsequence
        
        boolean hasZero = false;
        for (int x : nums) {
            if (x == 0) {
                hasZero = true;
                continue;
            }
            boolean[][][] ndp = new boolean[maxSum + 1][2][limit + 1];
            for (int s = 0; s <= maxSum; s++) {
                for (int p = 0; p < 2; p++) {
                    System.arraycopy(dp[s][p], 0, ndp[s][p], 0, limit + 1);
                }
            }
            for (int s = 0; s <= maxSum; s++) {
                for (int p = 0; p < 2; p++) {
                    for (int prod = 1; prod <= limit; prod++) {
                        if (!dp[s][p][prod]) continue;
                        int nsum = p == 0 ? s + x : s - x;
                        if (nsum < 0 || nsum > maxSum) continue;
                        int nprod = prod * x;
                        if (nprod <= limit) {
                            int np = p ^ 1;
                            ndp[nsum][np][nprod] = true;
                        }
                    }
                }
            }
            dp = ndp;
        }
        
        int ans = -1;
        if (offset + k >= 0 && offset + k <= maxSum) {
            for (int prod = 1; prod <= limit; prod++) {
                if (dp[offset + k][0][prod] || dp[offset + k][1][prod]) {
                    ans = Math.max(ans, prod);
                }
            }
        }
        if (k == 0 && hasZero) ans = Math.max(ans, 0);
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int limit, int output) {
        return output == maxProduct(nums, k, limit);
    }
}