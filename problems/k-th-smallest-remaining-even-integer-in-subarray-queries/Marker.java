import java.util.*;
class Marker {
    private int[] prefEven;
    public int[] kthRemainingInteger(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        prefEven = new int[n + 1];
        for (int i = 0; i < n; i++) prefEven[i + 1] = prefEven[i] + (nums[i] % 2 == 0 ? 1 : 0);
        for (int qi = 0; qi < m; qi++) {
            int l = queries[qi][0], r = queries[qi][1], k = queries[qi][2];
            long low = 2, high = 4000000000L;
            while (low < high) {
                long mid = low + (high - low) / 2;
                if (mid % 2 == 1) mid--;
                long totalEvens = mid / 2;
                long removedEvens = countEvensInRange(nums, l, r, mid);
                long remaining = totalEvens - removedEvens;
                if (remaining >= k) high = mid;
                else low = mid + 2;
            }
            ans[qi] = (int) low;
        }
        return ans;
    }

    private long countEvensInRange(int[] nums, int l, int r, long maxVal) {
        // count even numbers in nums[l..r] that are <= maxVal
        // nums is strictly increasing
            // last index in [l, r] with nums[i] <= maxVal via binary search on long
            int left = l, right = r, last = l - 1;
            while (left <= right) {
                int m = left + (right - left) / 2;
                if ((long) nums[m] <= maxVal) { last = m; left = m + 1; }
                else right = m - 1;
            }
            if (last < l) return 0;
            return prefEven[last + 1] - prefEven[l];
    }

    public boolean isCorrect(int[] nums, int[][] queries, int[] output) {
        if (output == null) return false;
        int[] expected = kthRemainingInteger(nums, queries);
        return Arrays.equals(expected, output);
    }
}