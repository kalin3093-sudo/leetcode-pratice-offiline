import java.util.*;
class Marker {
    static class SegTree {
        int n;
        int[] tree;
        SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            Arrays.fill(tree, Integer.MAX_VALUE);
        }
        void build(int v, int l, int r, int[] values) {
            if (l == r) { tree[v] = values[l]; return; }
            int m = (l + r) >> 1;
            build(v << 1, l, m, values);
            build(v << 1 | 1, m + 1, r, values);
            tree[v] = Math.min(tree[v << 1], tree[v << 1 | 1]);
        }
        void update(int v, int l, int r, int idx) {
            if (l == r) { tree[v] = Integer.MAX_VALUE; return; }
            int m = (l + r) >> 1;
            if (idx <= m) update(v << 1, l, m, idx);
            else update(v << 1 | 1, m + 1, r, idx);
            tree[v] = Math.min(tree[v << 1], tree[v << 1 | 1]);
        }
        int query(int v, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return Integer.MAX_VALUE;
            if (ql <= l && r <= qr) return tree[v];
            int m = (l + r) >> 1;
            return Math.min(query(v << 1, l, m, ql, qr), query(v << 1 | 1, m + 1, r, ql, qr));
        }
    }
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> {
            if (baskets[a] != baskets[b]) return baskets[a] - baskets[b];
            return a - b;
        });
        int[] sortedCap = new int[n];
        int[] origIdx = new int[n];
        int[] posInSorted = new int[n];
        for (int i = 0; i < n; i++) {
            sortedCap[i] = baskets[idx[i]];
            origIdx[i] = idx[i];
            posInSorted[idx[i]] = i;
        }
        SegTree st = new SegTree(n);
        st.build(1, 0, n - 1, origIdx);
        int unplaced = 0;
        for (int f : fruits) {
            int pos = Arrays.binarySearch(sortedCap, f);
            if (pos < 0) pos = -pos - 1;
            if (pos >= n) { unplaced++; continue; }
            int minOrig = st.query(1, 0, n - 1, pos, n - 1);
            if (minOrig == Integer.MAX_VALUE) { unplaced++; continue; }
            st.update(1, 0, n - 1, posInSorted[minOrig]);
        }
        return unplaced;
    }
    public boolean isCorrect(int[] fruits, int[] baskets, int output) {
        return numOfUnplacedFruits(fruits, baskets) == output;
    }
}