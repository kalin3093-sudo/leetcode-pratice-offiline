import java.util.*;

class Marker {
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        long[] v = new long[n];
        for (int i = 0; i < n; i++) v[i] = Math.abs(nums[i]);
        Arrays.sort(v);
        long ans = 0;
        int j = 1;
        for (int i = 0; i < n; i++) {
            if (j < i + 1) j = i + 1;
            while (j < n && v[j] <= 2 * v[i]) j++;
            ans += j - i - 1;
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == perfectPairs(nums);
    }
}
