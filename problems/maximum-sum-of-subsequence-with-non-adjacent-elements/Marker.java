import java.util.*;
class Marker {
    static final int MOD = 1_000_000_007;
    
    private static class Node {
        long a00, a01, a10, a11;
        Node() { a00 = a01 = a10 = a11 = 0; }
        Node(long a00, long a01, long a10, long a11) {
            this.a00 = a00; this.a01 = a01; this.a10 = a10; this.a11 = a11;
        }
    }
    
    private static Node merge(Node L, Node R) {
        Node res = new Node();
        res.a00 = Math.max(L.a00 + R.a00, L.a01 + R.a10);
        res.a01 = Math.max(L.a00 + R.a01, L.a01 + R.a11);
        res.a10 = Math.max(L.a10 + R.a00, L.a11 + R.a10);
        res.a11 = Math.max(L.a10 + R.a01, L.a11 + R.a11);
        return res;
    }
    
    private static Node makeLeaf(int val) {
        if (val > 0) {
            return new Node(0, 0, val, val);
        } else {
            return new Node(0, 0, 0, 0);
        }
    }
    
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        int size = 1;
        while (size < n) size <<= 1;
        Node[] seg = new Node[2 * size];
        for (int i = 0; i < 2 * size; i++) seg[i] = new Node();
        
        for (int i = 0; i < n; i++) {
            seg[size + i] = makeLeaf(nums[i]);
        }
        for (int i = size - 1; i > 0; i--) {
            seg[i] = merge(seg[i << 1], seg[i << 1 | 1]);
        }
        
        long total = 0;
        for (int[] q : queries) {
            int pos = q[0];
            int val = q[1];
            int p = size + pos;
            seg[p] = makeLeaf(val);
            for (p >>= 1; p > 0; p >>= 1) {
                seg[p] = merge(seg[p << 1], seg[p << 1 | 1]);
            }
            long ans = Math.max(seg[1].a00, Math.max(seg[1].a01, Math.max(seg[1].a10, seg[1].a11)));
            total = (total + ans) % MOD;
        }
        return (int) total;
    }

    public boolean isCorrect(int[] nums, int[][] queries, int output) {
        int expected = maximumSumSubsequence(nums, queries);
        return expected == output;
    }
}