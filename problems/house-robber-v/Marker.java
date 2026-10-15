import java.util.*;
class Marker {
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        if (n == 1) return nums[0];
        long[] dp = new long[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i];
            dp[i] = Math.max(dp[i], dp[i - 1]);
            if (colors[i] != colors[i - 1]) dp[i] = Math.max(dp[i], nums[i] + dp[i - 1]);
            if (i >= 2) dp[i] = Math.max(dp[i], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }
    public boolean isCorrect(int[] nums, int[] colors, long output) {
        return rob(nums, colors) == output;
    }
}