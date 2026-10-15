import java.util.*;
class Marker {
    public int lengthOfLIS(int[] nums, int k) {
        int maxVal = 100000;
        int[] seg = new int[4 * (maxVal + 2)];
        
        int ans = 1;
        for (int x : nums) {
            int l = Math.max(1, x - k);
            int r = x - 1;
            int best = query(seg, 1, 1, maxVal, l, r);
            int curr = best + 1;
            ans = Math.max(ans, curr);
            update(seg, 1, 1, maxVal, x, curr);
        }
        return ans;
    }

    private int query(int[] seg, int node, int lo, int hi, int l, int r) {
        if (l > r) return 0;
        if (l <= lo && hi <= r) return seg[node];
        int mid = (lo + hi) >> 1;
        int res = 0;
        if (l <= mid) res = Math.max(res, query(seg, node << 1, lo, mid, l, r));
        if (r > mid) res = Math.max(res, query(seg, node << 1 | 1, mid + 1, hi, l, r));
        return res;
    }

    private void update(int[] seg, int node, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            seg[node] = Math.max(seg[node], val);
            return;
        }
        int mid = (lo + hi) >> 1;
        if (idx <= mid) update(seg, node << 1, lo, mid, idx, val);
        else update(seg, node << 1 | 1, mid + 1, hi, idx, val);
        seg[node] = Math.max(seg[node << 1], seg[node << 1 | 1]);
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return lengthOfLIS(nums, k) == output;
    }
}