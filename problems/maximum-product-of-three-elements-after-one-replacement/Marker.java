import java.util.*;

class Marker {
    public long maxProduct(int[] nums) {
        int n = nums.length;
        long maxAbs = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long abs = Math.abs((long) nums[i] * nums[j]);
                maxAbs = Math.max(maxAbs, abs);
            }
        }
        return maxAbs * 100000;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maxProduct(nums);
    }
}