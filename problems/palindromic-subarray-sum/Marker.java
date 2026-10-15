import java.util.*;

class Marker {
    public long getSum(int[] nums) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        long best = 0;
        int[] d1 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && nums[i - k] == nums[i + k]) k++;
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
            long s = pref[i + d1[i]] - pref[i - d1[i] + 1];
            if (s > best) best = s;
        }
        int[] d2 = new int[n];
        for (int i = 0, l = 0, r = -1; i < n; i++) {
            int k = (i > r) ? 0 : Math.min(d2[l + r - i + 1], r - i + 1);
            while (i - k - 1 >= 0 && i + k < n && nums[i - k - 1] == nums[i + k]) k++;
            d2[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
            if (d2[i] > 0) {
                long s = pref[i + d2[i]] - pref[i - d2[i]];
                if (s > best) best = s;
            }
        }
        return best;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == getSum(nums);
    }
}
