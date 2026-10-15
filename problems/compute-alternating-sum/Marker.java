import java.util.*;
class Marker {
    public int alternatingSum(int[] nums) {
        int s = 0;
        for (int i = 0; i < nums.length; i++) s += (i % 2 == 0 ? nums[i] : -nums[i]);
        return s;
    }

    public boolean isCorrect(int[] nums, int output) {
        return alternatingSum(nums) == output;
    }
}
