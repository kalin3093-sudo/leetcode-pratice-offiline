import java.util.*;
class Marker {
    public long minInversionCount(int[] nums, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = 0; i < n; i++) rank.put(sorted[i], i + 1);

        long[] bit = new long[n + 1];
        long cur = 0, ans = Long.MAX_VALUE;
        int sz = 0;
        for (int i = 0; i < n; i++) {
            int r = rank.get(nums[i]);
            cur += sz - query(bit, r); // existing window elements greater than nums[i]
            add(bit, r, 1);
            sz++;
            if (sz == k) {
                ans = Math.min(ans, cur);
                int fr = rank.get(nums[i - k + 1]);
                cur -= query(bit, fr - 1); // window elements smaller than the leaving front
                add(bit, fr, -1);
                sz--;
            }
        }
        return ans;
    }

    private void add(long[] bit, int i, long v) {
        for (; i < bit.length; i += i & -i) bit[i] += v;
    }

    private long query(long[] bit, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = minInversionCount(nums, k);
        return expected == output;
    }
}
