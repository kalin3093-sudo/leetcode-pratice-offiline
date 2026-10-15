import java.util.*;
class Marker {
    private static final long MOD = 1000000007L;
    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] b1 = new long[n + 2], b2 = new long[n + 2];
        Map<Integer, Integer> last = new HashMap<>();
        long f = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            int p = last.getOrDefault(nums[i], -1);
            long sumD = rangeSum(b1, b2, p + 1, i);
            f = (f + 2 * sumD + (i - p)) % MOD;
            ans = (ans + f) % MOD;
            rangeAdd(b1, b2, p + 1, i, 1);
            last.put(nums[i], i);
        }
        return (int) ans;
    }
    private void rangeAdd(long[] b1, long[] b2, int l, int r, long v) {
        if (l > r) return;
        l++;
        r++;
        add(b1, l, v);
        add(b1, r + 1, -v);
        add(b2, l, v * (l - 1));
        add(b2, r + 1, -v * r);
    }
    private long rangeSum(long[] b1, long[] b2, int l, int r) {
        if (l > r) return 0;
        l++;
        r++;
        return prefix(b1, b2, r) - prefix(b1, b2, l - 1);
    }
    private long prefix(long[] b1, long[] b2, int i) {
        return sum(b1, i) * i - sum(b2, i);
    }
    private long sum(long[] b, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += b[i];
        return s;
    }
    private void add(long[] b, int i, long v) {
        for (; i < b.length; i += i & -i) b[i] += v;
    }
    public boolean isCorrect(int[] nums, int output) {
        return output == sumCounts(nums);
    }
}
