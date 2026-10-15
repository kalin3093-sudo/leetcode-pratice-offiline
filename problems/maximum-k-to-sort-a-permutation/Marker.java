import java.util.*;

class Marker {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int ans = (1 << 30) - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == sortPermutation(nums);
    }
}