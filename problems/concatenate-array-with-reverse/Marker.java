import java.util.*;
class Marker {
    public int[] concatWithReverse(int[] nums) {
        int[] res = new int[2 * nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
            res[2 * nums.length - 1 - i] = nums[i];
        }
        return res;
    }

    public boolean isCorrect(int[] nums, int[] output) {
        if (output == null) return false;
        return Arrays.equals(concatWithReverse(nums), output);
    }
}
