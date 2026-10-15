import java.util.*;
class Marker {
    public int maxValidPairSum(int[] nums, int k) {
        int n = nums.length;
        int maxPrefix = nums[0];
        int ans = 0;
        
        for (int j = k; j < n; j++) {
            ans = Math.max(ans, maxPrefix + nums[j]);
            if (j - k + 1 < n) {
                maxPrefix = Math.max(maxPrefix, nums[j - k + 1]);
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maxValidPairSum(nums, k);
        return expected == output;
    }
}