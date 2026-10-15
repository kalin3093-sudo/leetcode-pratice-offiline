import java.util.*;
class Marker {
    private static class SegTree {
        int n;
        long[] tree;
        SegTree(int size) {
            n = 1;
            while (n < size) n <<= 1;
            tree = new long[2 * n];
            Arrays.fill(tree, Long.MIN_VALUE / 2);
        }
        void update(int pos, long val) {
            pos += n;
            tree[pos] = Math.max(tree[pos], val);
            for (pos >>= 1; pos > 0; pos >>= 1) {
                tree[pos] = Math.max(tree[pos << 1], tree[pos << 1 | 1]);
            }
        }
        long query(int l, int r) {
            if (l > r) return Long.MIN_VALUE / 2;
            long res = Long.MIN_VALUE / 2;
            for (l += n, r += n + 1; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) != 0) res = Math.max(res, tree[l++]);
                if ((r & 1) != 0) res = Math.max(res, tree[--r]);
            }
            return res;
        }
    }

    public long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;
        int maxVal = 100000;
        long[] dp0 = new long[n]; // last relation: up (prev < curr)
        long[] dp1 = new long[n]; // last relation: down (prev > curr)
        
        SegTree segLess = new SegTree(maxVal + 2); // stores dp1 at val, query [1, x-1]
        SegTree segGreater = new SegTree(maxVal + 2); // stores dp0 at val, query [x+1, maxVal]
        
        long ans = Long.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            // Add i - k to segment trees
            if (i - k >= 0) {
                int idx = i - k;
                int val = nums[idx];
                segLess.update(val, dp1[idx]);
                segGreater.update(val, dp0[idx]);
            }
            
            int x = nums[i];
            // Start new subsequence
            dp0[i] = x;
            dp1[i] = x;
            
            // Extend from previous with distance >= k
            long bestLess = segLess.query(1, x - 1); // prev val < x, need prev relation DOWN (dp1)
            long bestGreater = segGreater.query(x + 1, maxVal); // prev val > x, need prev relation UP (dp0)
            
            if (bestLess > Long.MIN_VALUE / 4) dp0[i] = Math.max(dp0[i], bestLess + x);
            if (bestGreater > Long.MIN_VALUE / 4) dp1[i] = Math.max(dp1[i], bestGreater + x);
            
            ans = Math.max(ans, Math.max(dp0[i], dp1[i]));
        }
        
        return ans;
    }

    public boolean isCorrect(int[] nums, int k, long output) {
        long expected = maxAlternatingSum(nums, k);
        return expected == output;
    }
}