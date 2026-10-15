import java.util.*;

class Marker {
    public int minOperations(int[] nums) {
        boolean allEqual = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[0]) {
                allEqual = false;
                break;
            }
        }
        return allEqual ? 0 : 1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minOperations(nums) == output;
    }
}