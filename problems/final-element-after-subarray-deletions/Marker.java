import java.util.*;
class Marker {
    public int finalElement(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(nums[0], nums[nums.length - 1]);
    }

    public boolean isCorrect(int[] nums, int output) {
        return finalElement(nums) == output;
    }
}