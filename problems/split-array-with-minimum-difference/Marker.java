import java.util.*;
class Marker {
    public long splitArray(int[] nums) {
        int n = nums.length;
        boolean[] inc = new boolean[n], dec = new boolean[n];
        inc[0] = true;
        for (int i = 1; i < n; i++) inc[i] = inc[i - 1] && nums[i] > nums[i - 1];
        dec[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) dec[i] = dec[i + 1] && nums[i] > nums[i + 1];
        long total = 0;
        for (int x : nums) total += x;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        long best = Long.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (inc[i] && dec[i + 1]) {
                long left = pref[i + 1], right = total - pref[i + 1];
                best = Math.min(best, Math.abs(left - right));
            }
        }
        return best == Long.MAX_VALUE ? -1 : best;
    }
    public boolean isCorrect(int[] nums, long output) {
        return output == splitArray(nums);
    }
}
