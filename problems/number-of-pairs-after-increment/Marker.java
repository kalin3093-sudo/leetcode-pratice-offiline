import java.util.*;

class Marker {
    public int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums2.length;
        int B = (int) Math.sqrt(n) + 1;
        int nb = (n + B - 1) / B;
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nums2[i];
        long[] lazy = new long[nb];
        @SuppressWarnings("unchecked")
        HashMap<Long, Integer>[] cnt = new HashMap[nb];
        for (int b = 0; b < nb; b++) {
            cnt[b] = new HashMap<>();
            int lo = b * B, hi = Math.min(n, lo + B);
            for (int i = lo; i < hi; i++) {
                cnt[b].merge(arr[i], 1, Integer::sum);
            }
        }
        HashMap<Integer, Integer> c1 = new HashMap<>();
        for (int v : nums1) c1.merge(v, 1, Integer::sum);
        ArrayList<Integer> res = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1], y = q[2], val = q[3];
                int bx = x / B, by = y / B;
                if (bx == by) {
                    for (int i = x; i <= y; i++) dec(cnt[bx], arr[i]);
                    for (int i = x; i <= y; i++) {
                        arr[i] += val;
                        cnt[bx].merge(arr[i], 1, Integer::sum);
                    }
                } else {
                    int ex = (bx + 1) * B;
                    for (int i = x; i < ex; i++) dec(cnt[bx], arr[i]);
                    for (int i = x; i < ex; i++) {
                        arr[i] += val;
                        cnt[bx].merge(arr[i], 1, Integer::sum);
                    }
                    for (int b = bx + 1; b < by; b++) lazy[b] += val;
                    int sy = by * B;
                    for (int i = sy; i <= y; i++) dec(cnt[by], arr[i]);
                    for (int i = sy; i <= y; i++) {
                        arr[i] += val;
                        cnt[by].merge(arr[i], 1, Integer::sum);
                    }
                }
            } else {
                int tot = q[1];
                long ans = 0;
                for (Map.Entry<Integer, Integer> e : c1.entrySet()) {
                    long target = (long) tot - e.getKey();
                    int c = 0;
                    for (int b = 0; b < nb; b++) {
                        c += cnt[b].getOrDefault(target - lazy[b], 0);
                    }
                    ans += (long) e.getValue() * c;
                }
                res.add((int) ans);
            }
        }
        int[] out = new int[res.size()];
        for (int i = 0; i < out.length; i++) out[i] = res.get(i);
        return out;
    }

    private void dec(HashMap<Long, Integer> m, long key) {
        int v = m.getOrDefault(key, 0) - 1;
        if (v == 0) m.remove(key);
        else m.put(key, v);
    }

    public boolean isCorrect(int[] nums1, int[] nums2, int[][] queries, int[] output) {
        int[] expected = numberOfPairs(nums1, nums2, queries);
        if (output == null || output.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (output[i] != expected[i]) return false;
        }
        return true;
    }
}
