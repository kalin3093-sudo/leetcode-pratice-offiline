import java.util.*;
class Marker {
    private static final long MOD = 1000000007L;
    public int totalBeauty(int[] nums) {
        int n = nums.length, maxV = 0;
        for (int x : nums) maxV = Math.max(maxV, x);
        Map<Integer, List<Integer>> byG = new HashMap<>();
        for (int x : nums) {
            for (int d = 1; d * d <= x; d++) {
                if (x % d == 0) {
                    byG.computeIfAbsent(d, key -> new ArrayList<>()).add(x / d);
                    int e = x / d;
                    if (e != d) byG.computeIfAbsent(e, key -> new ArrayList<>()).add(x / e);
                }
            }
        }
        long[] cnt = new long[maxV + 1];
        for (Map.Entry<Integer, List<Integer>> en : byG.entrySet()) {
            List<Integer> vals = en.getValue();
            List<Integer> sorted = new ArrayList<>(vals);
            Collections.sort(sorted);
            Map<Integer, Integer> rank = new HashMap<>();
            int r = 0;
            for (int v : sorted) {
                if (!rank.containsKey(v)) rank.put(v, ++r);
            }
            long[] bit = new long[r + 1];
            long total = 0;
            for (int v : vals) {
                int rk = rank.get(v);
                long add = 1 + query(bit, rk - 1);
                if (add >= MOD) add -= MOD;
                update(bit, rk, add);
                total += add;
                if (total >= MOD) total -= MOD;
            }
            cnt[en.getKey()] = total;
        }
        long[] f = new long[maxV + 1];
        long ans = 0;
        for (int g = maxV; g >= 1; g--) {
            long val = cnt[g];
            for (int m = 2 * g; m <= maxV; m += g) val -= f[m];
            val %= MOD;
            if (val < 0) val += MOD;
            f[g] = val;
            ans = (ans + val * g) % MOD;
        }
        return (int) ans;
    }
    private long query(long[] bit, int i) {
        long s = 0;
        for (; i > 0; i -= i & -i) s += bit[i];
        return s;
    }
    private void update(long[] bit, int i, long v) {
        for (; i < bit.length; i += i & -i) {
            bit[i] += v;
            if (bit[i] >= MOD) bit[i] -= MOD;
        }
    }
    public boolean isCorrect(int[] nums, int output) {
        return output == totalBeauty(nums);
    }
}
