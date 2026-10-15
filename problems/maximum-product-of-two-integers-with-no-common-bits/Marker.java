import java.util.*;

class Marker {
    public long maxProduct(int[] nums) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        int B = 0;
        while ((1 << B) <= maxVal) B++;
        int M = 1 << B;
        int[] dp = new int[M];
        for (int x : nums) {
            dp[x] = Math.max(dp[x], x);
        }
        for (int b = 0; b < B; b++) {
            for (int m = 0; m < M; m++) {
                if ((m & (1 << b)) != 0) {
                    dp[m] = Math.max(dp[m], dp[m ^ (1 << b)]);
                }
            }
        }
        long ans = 0;
        for (int x : nums) {
            int cm = (~x) & (M - 1);
            int partner = dp[cm];
            if (partner > 0) {
                ans = Math.max(ans, (long) x * partner);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maxProduct(nums);
    }
}