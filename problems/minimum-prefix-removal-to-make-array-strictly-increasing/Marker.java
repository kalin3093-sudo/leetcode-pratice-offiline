import java.util.*;
class Marker {
    public int minimumPrefixLength(int[] nums) {
        int n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                return i + 1;
            }
        }
        return 0;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minimumPrefixLength(nums) == output;
    }
}