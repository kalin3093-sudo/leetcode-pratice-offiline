import java.util.*;
class Marker {
    public long maxTotalValue(int[] nums, int k) {
        int max = nums[0];
        int min = nums[0];
        for (int x : nums) {
            if (x > max) max = x;
            if (x < min) min = x;
        }
        return (long) k * (max - min);
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxTotalValue(nums, k);
        return expected == output;
    }
}