import java.util.*;

class Marker {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        boolean found = false;
        for (int x : nums) if (x == target) { found = true; break; }
        if (!found) return 0;

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (nums[i] == target) ? 1 : -1;

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + arr[i];

        List<Long> all = new ArrayList<>();
        for (long v : pref) all.add(v);
        Collections.sort(all);
        all = new ArrayList<>(new LinkedHashSet<>(all));
        Map<Long, Integer> comp = new HashMap<>();
        for (int i = 0; i < all.size(); i++) comp.put(all.get(i), i + 1);
        int M = all.size() + 2;

        int[] bit = new int[M];
        long ans = 0;
        add(bit, comp.get(pref[0]), 1);
        for (int i = 1; i <= n; i++) {
            int ci = comp.get(pref[i]);
            ans += query(bit, ci - 1);
            add(bit, ci, 1);
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

    public boolean isCorrect(int[] nums, int target, long output) {
        return output == countMajoritySubarrays(nums, target);
    }
}