import java.util.*;
class Marker {
    public boolean checkEqualPartitions(int[] nums, long target) {
        int n = nums.length;
        int full = 1 << n;
        long[] prod = new long[full];
        prod[0] = 1;
        for (int mask = 1; mask < full; mask++) {
            int lb = mask & -mask;
            int i = Integer.numberOfTrailingZeros(lb);
            prod[mask] = prod[mask ^ lb] * nums[i];
            if (prod[mask] > target) prod[mask] = target + 1;
        }
        for (int mask = 1; mask < full - 1; mask++) {
            if (prod[mask] == target && prod[full - 1 ^ mask] == target) return true;
        }
        return false;
    }
    public boolean isCorrect(int[] nums, long target, boolean output) {
        return output == checkEqualPartitions(nums, target);
    }
}
