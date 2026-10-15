import java.util.*;
class Marker {
    private static final int MAXV = 1000000;
    private boolean[] isPrime;
    private int n;
    private int[] nums;
    private Map<Integer, TreeSet<Integer>> primeIndices;
    private SegTree seg;
    
    public int[] maximumCount(int[] nums, int[][] queries) {
        this.n = nums.length;
        this.nums = nums.clone();
        this.isPrime = sieve(MAXV);
        this.primeIndices = new HashMap<>();
        this.seg = new SegTree(n + 1);
        
        for (int i = 0; i < n; i++) {
            addIndex(i, nums[i]);
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            update(idx, val);
            int totalPrimes = primeIndices.size();
            int maxOverlap = seg.queryMax(0, n);
            ans[i] = totalPrimes + maxOverlap;
        }
        return ans;
    }
    
    private void update(int idx, int newVal) {
        int oldVal = nums[idx];
        if (oldVal == newVal) return;
        nums[idx] = newVal;
        
        removeIndex(idx, oldVal);
        addIndex(idx, newVal);
    }
    
    private void addIndex(int idx, int val) {
        if (val <= MAXV && isPrime[val]) {
            TreeSet<Integer> set = primeIndices.computeIfAbsent(val, k -> new TreeSet<>());
            boolean hadSegment = set.size() >= 2;
            int oldFirst = hadSegment ? set.first() : -1;
            int oldLast = hadSegment ? set.last() : -1;
            
            set.add(idx);
            
            if (set.size() >= 2) {
                int newFirst = set.first();
                int newLast = set.last();
                if (hadSegment) {
                    if (newFirst != oldFirst || newLast != oldLast) {
                        if (oldFirst + 1 <= oldLast) seg.add(oldFirst + 1, oldLast, -1);
                        if (newFirst + 1 <= newLast) seg.add(newFirst + 1, newLast, 1);
                    }
                } else {
                    if (newFirst + 1 <= newLast) seg.add(newFirst + 1, newLast, 1);
                }
            }
        }
    }
    
    private void removeIndex(int idx, int val) {
        if (val <= MAXV && isPrime[val]) {
            TreeSet<Integer> set = primeIndices.get(val);
            if (set == null) return;
            
            boolean hadSegment = set.size() >= 2;
            int oldFirst = hadSegment ? set.first() : -1;
            int oldLast = hadSegment ? set.last() : -1;
            
            set.remove(idx);
            
            if (set.size() >= 2) {
                int newFirst = set.first();
                int newLast = set.last();
                if (hadSegment) {
                    if (newFirst != oldFirst || newLast != oldLast) {
                        if (oldFirst + 1 <= oldLast) seg.add(oldFirst + 1, oldLast, -1);
                        if (newFirst + 1 <= newLast) seg.add(newFirst + 1, newLast, 1);
                    }
                } else {
                    if (newFirst + 1 <= newLast) seg.add(newFirst + 1, newLast, 1);
                }
            } else if (hadSegment) {
                if (oldFirst + 1 <= oldLast) seg.add(oldFirst + 1, oldLast, -1);
            }
            
            if (set.isEmpty()) {
                primeIndices.remove(val);
            }
        }
    }
    
    private boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
    
    static class SegTree {
        int n;
        int[] tree, lazy;
        
        SegTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }
        
        void add(int l, int r, int val) {
            if (l > r) return;
            add(1, 0, n - 1, l, r, val);
        }
        
        private void add(int node, int lo, int hi, int l, int r, int val) {
            if (l > r) return;
            if (l <= lo && hi <= r) {
                tree[node] += val;
                lazy[node] += val;
                return;
            }
            push(node);
            int mid = (lo + hi) >> 1;
            if (l <= mid) add(node << 1, lo, mid, l, r, val);
            if (r > mid) add(node << 1 | 1, mid + 1, hi, l, r, val);
            tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
        }
        
        private void push(int node) {
            if (lazy[node] != 0) {
                int v = lazy[node];
                tree[node << 1] += v;
                lazy[node << 1] += v;
                tree[node << 1 | 1] += v;
                lazy[node << 1 | 1] += v;
                lazy[node] = 0;
            }
        }
        
        int queryMax(int l, int r) {
            if (l > r) return 0;
            return queryMax(1, 0, n - 1, l, r);
        }
        
        private int queryMax(int node, int lo, int hi, int l, int r) {
            if (l > r) return 0;
            if (l <= lo && hi <= r) return tree[node];
            push(node);
            int mid = (lo + hi) >> 1;
            int res = 0;
            if (l <= mid) res = Math.max(res, queryMax(node << 1, lo, mid, l, r));
            if (r > mid) res = Math.max(res, queryMax(node << 1 | 1, mid + 1, hi, l, r));
            return res;
        }
    }

    public boolean isCorrect(int[] nums, int[][] queries, int[] output) {
        return Arrays.equals(maximumCount(nums, queries), output);
    }
}