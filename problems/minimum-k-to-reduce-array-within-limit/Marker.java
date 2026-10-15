import java.util.*;

class Marker {
    public int minimumK(int[] nums) {
        int lo = 1, hi = 100000;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            long ops = 0;
            for (int x : nums) {
                ops += (x + mid - 1) / mid;
            }
            if (ops <= 1L * mid * mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minimumK(nums) == output;
    }
}