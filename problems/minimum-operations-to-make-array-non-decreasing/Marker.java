import java.util.*;
class Marker {
    public long minOperations(int[] nums) {
        long ans = 0;
        for (int i = 0; i + 1 < nums.length; i++)
            if (nums[i] > nums[i + 1]) ans += nums[i] - nums[i + 1];
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == minOperations(nums);
    }
}
