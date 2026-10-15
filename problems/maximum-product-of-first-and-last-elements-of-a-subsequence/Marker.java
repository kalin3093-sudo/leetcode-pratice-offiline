import java.util.*;

class Marker {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        long ans = Long.MIN_VALUE;
        int maxSuf = Integer.MIN_VALUE;
        int minSuf = Integer.MAX_VALUE;
        for (int i = n - m; i >= 0; i--) {
            maxSuf = Math.max(maxSuf, nums[i + m - 1]);
            minSuf = Math.min(minSuf, nums[i + m - 1]);
            ans = Math.max(ans, (long) nums[i] * maxSuf);
            ans = Math.max(ans, (long) nums[i] * minSuf);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int m, long output) {
        return output == maximumProduct(nums, m);
    }
}