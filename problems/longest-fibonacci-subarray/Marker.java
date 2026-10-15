import java.util.*;
class Marker {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;
        int maxLen = 2;
        int currLen = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) {
                currLen++;
            } else {
                currLen = 2;
            }
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }

    public boolean isCorrect(int[] nums, int output) {
        return longestSubarray(nums) == output;
    }
}