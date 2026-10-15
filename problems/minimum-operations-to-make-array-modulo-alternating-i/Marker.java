import java.util.*;
class Marker {
    public int minOperations(int[] nums, int k) {
        int best = Integer.MAX_VALUE;
        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                if (x == y) continue;
                int c = 0;
                for (int i = 0; i < nums.length; i++) {
                    int r = ((nums[i] % k) + k) % k;
                    int t = (i % 2 == 0) ? x : y;
                    c += Math.min((t - r + k) % k, (r - t + k) % k);
                }
                best = Math.min(best, c);
            }
        }
        return best;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return output == minOperations(nums, k);
    }
}
