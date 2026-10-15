import java.util.*;
class Marker {
    static class Node {
        int[] freq;
        int total;
        Node(int k) {
            freq = new int[k];
            total = 1;
        }
    }
    int k;
    Node[] tree;
    int n;
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1, nums);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1], start = queries[i][2], xi = queries[i][3];
            update(1, 0, n - 1, idx, val % k);
            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = res.freq[xi];
        }
        return ans;
    }
    void build(int v, int l, int r, int[] nums) {
        tree[v] = new Node(k);
        if (l == r) {
            tree[v].freq[nums[l] % k] = 1;
            tree[v].total = nums[l] % k;
            return;
        }
        int m = (l + r) >> 1;
        build(v << 1, l, m, nums);
        build(v << 1 | 1, m + 1, r, nums);
        pull(v);
    }
    void pull(int v) {
        Node L = tree[v << 1], R = tree[v << 1 | 1], T = tree[v];
        T.total = (L.total * R.total) % k;
        Arrays.fill(T.freq, 0);
        for (int x = 0; x < k; x++) T.freq[x] = L.freq[x];
        for (int y = 0; y < k; y++) {
            int x = (L.total * y) % k;
            T.freq[x] = (T.freq[x] + R.freq[y]);
        }
    }
    void update(int v, int l, int r, int idx, int val) {
        if (l == r) {
            tree[v].freq = new int[k];
            tree[v].freq[val] = 1;
            tree[v].total = val;
            return;
        }
        int m = (l + r) >> 1;
        if (idx <= m) update(v << 1, l, m, idx, val);
        else update(v << 1 | 1, m + 1, r, idx, val);
        pull(v);
    }
    Node query(int v, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return tree[v];
        int m = (l + r) >> 1;
        if (qr <= m) return query(v << 1, l, m, ql, qr);
        if (ql > m) return query(v << 1 | 1, m + 1, r, ql, qr);
        Node L = query(v << 1, l, m, ql, qr);
        Node R = query(v << 1 | 1, m + 1, r, ql, qr);
        Node T = new Node(k);
        T.total = (L.total * R.total) % k;
        for (int x = 0; x < k; x++) T.freq[x] = L.freq[x];
        for (int y = 0; y < k; y++) {
            int x = (L.total * y) % k;
            T.freq[x] += R.freq[y];
        }
        return T;
    }
    public boolean isCorrect(int[] nums, int k, int[][] queries, int[] output) {
        return Arrays.equals(resultArray(nums, k, queries), output);
    }
}