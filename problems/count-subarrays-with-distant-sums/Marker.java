import java.util.*;

class Marker {
    public long distantSubarrays(int[] nums, int goal, int k) {
        int n = nums.length;
        if (k == 0) {
            return 1L * n * (n + 1) / 2;
        }

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];

        List<Long> all = new ArrayList<>();
        for (long v : pref) {
            all.add(v);
            all.add(v - goal - k);
            all.add(v - goal + k);
        }
        Collections.sort(all);
        all = new ArrayList<>(new LinkedHashSet<>(all));
        Map<Long, Integer> comp = new HashMap<>();
        for (int i = 0; i < all.size(); i++) comp.put(all.get(i), i + 1);
        int M = all.size() + 2;

        int[] bit = new int[M];
        add(bit, comp.get(pref[0]), 1);
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            long s = pref[i];
            long L = s - goal - k;
            long R = s - goal + k;
            int lo = comp.get(L);
            int hi = comp.get(R);
            ans += query(bit, lo);
            ans += query(bit, M - 1) - query(bit, hi - 1);
            add(bit, comp.get(s), 1);
        }
        return ans;
    }

    private void add(int[] bit, int i, int delta) {
        for (; i < bit.length; i += i & -i) bit[i] += delta;
    }

    private int query(int[] bit, int i) {
        int sum = 0;
        for (; i > 0; i -= i & -i) sum += bit[i];
        return sum;
    }

    public boolean isCorrect(int[] nums, int goal, int k, long output) {
        return output == distantSubarrays(nums, goal, k);
    }
}