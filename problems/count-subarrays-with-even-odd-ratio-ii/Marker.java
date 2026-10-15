import java.util.*;

class Marker {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] % 2 == 0 ? b : -a);
        }

        long[] sorted = pref.clone();
        Arrays.sort(sorted);
        Map<Long, Integer> comp = new HashMap<>();
        int idx = 0;
        for (long v : sorted) {
            if (!comp.containsKey(v)) comp.put(v, idx++);
        }

        int[] bit = new int[idx + 2];
        autoUpdate(bit, comp.get(pref[0]), 1);
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int ci = comp.get(pref[i]);
            ans += query(bit, ci, idx);
            autoUpdate(bit, ci, 1);
        }
        return ans;
    }

    private void autoUpdate(int[] bit, int i, int delta) {
        for (i++; i < bit.length; i += i & -i) bit[i] += delta;
    }

    private int query(int[] bit, int l, int r) {
        return query(bit, r) - query(bit, l - 1);
    }

    private int query(int[] bit, int i) {
        int sum = 0;
        for (i++; i > 0; i -= i & -i) sum += bit[i];
        return sum;
    }

    public boolean isCorrect(int[] nums, int a, int b, long output) {
        return output == countRatioSubarrays(nums, a, b);
    }
}