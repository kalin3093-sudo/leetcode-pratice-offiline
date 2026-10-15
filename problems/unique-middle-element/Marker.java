import java.util.*;

class Marker {
    public boolean isMiddleElementUnique(int[] nums) {
        int mid = nums[nums.length / 2];
        int c = 0;
        for (int v : nums)
            if (v == mid) c++;
        return c == 1;
    }

    public boolean isCorrect(int[] nums, boolean output) {
        return output == isMiddleElementUnique(nums);
    }
}
