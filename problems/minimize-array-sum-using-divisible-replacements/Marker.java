import java.util.*;
class Marker {
    public long minArraySum(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        boolean[] present = new boolean[max + 1];
        for (int x : nums) present[x] = true;
        // best[v] = smallest originally-present divisor of v
        long[] best = new long[max + 1];
        for (int d = 1; d <= max; d++) {
            if (!present[d]) continue;
            for (int m = d; m <= max; m += d) {
                if (present[m] && best[m] == 0) best[m] = d;
            }
        }
        long sum = 0;
        for (int x : nums) sum += best[x];
        return sum;
    }

    public boolean isCorrect(int[] nums, long output) {
        return minArraySum(nums) == output;
    }
}
