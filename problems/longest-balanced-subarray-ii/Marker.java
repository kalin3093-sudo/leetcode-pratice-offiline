import java.util.*;
class Marker {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxVal = 100000;
        List<Integer>[] pos = new List[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) pos[nums[i]].add(i);
        
        // Segment tree for range add, query rightmost zero
        SegTree st = new SegTree(n);
        
        // Initialize: for each value, add its sign to [first_occurrence, n-1]
        for (int v = 1; v <= maxVal; v++) {
            if (!pos[v].isEmpty()) {
                int p = pos[v].get(0);
                int sign = (v % 2 == 0) ? -1 : 1;
                st.update(p, n - 1, sign);
            }
        }
        
        int ans = 0;
        // For each left index l
        for (int l = 0; l < n; l++) {
            // Query rightmost r >= l with balance 0
            int r = st.queryRightmostZero(l, n - 1);
            if (r != -1) ans = Math.max(ans, r - l + 1);
            
            // Move left pointer: remove nums[l]
            int v = nums[l];
            int sign = (v % 2 == 0) ? -1 : 1;
            // Find this occurrence in pos[v] and remove it
            List<Integer> list = pos[v];
            int idx = Collections.binarySearch(list, l);
            list.remove(idx);
            int next = list.isEmpty() ? n : list.get(0);
            // The value v was contributing sign to [l, next-1] (since first occurrence was l)
            // Now it contributes from next onwards, so we remove sign from [l, next-1]
            if (l <= next - 1) st.update(l, next - 1, -sign);
        }
        return ans;
    }
    
    class SegTree {
        int n;
        int[] mn, mx, lazy;
        
        SegTree(int n) {
            this.n = n;
            int size = 4 * n;
            mn = new int[size];
            mx = new int[size];
            lazy = new int[size];
        }
        
        void apply(int node, int val) {
            mn[node] += val;
            mx[node] += val;
            lazy[node] += val;
        }
        
        void push(int node) {
            if (lazy[node] != 0) {
                apply(node * 2, lazy[node]);
                apply(node * 2 + 1, lazy[node]);
                lazy[node] = 0;
            }
        }
        
        void pull(int node) {
            mn[node] = Math.min(mn[node * 2], mn[node * 2 + 1]);
            mx[node] = Math.max(mx[node * 2], mx[node * 2 + 1]);
        }
        
        void update(int l, int r, int val) {
            update(1, 0, n - 1, l, r, val);
        }
        
        void update(int node, int nl, int nr, int l, int r, int val) {
            if (l > nr || r < nl) return;
            if (l <= nl && nr <= r) {
                apply(node, val);
                return;
            }
            push(node);
            int mid = (nl + nr) / 2;
            update(node * 2, nl, mid, l, r, val);
            update(node * 2 + 1, mid + 1, nr, l, r, val);
            pull(node);
        }
        
        // Find rightmost index in [ql, qr] with value 0
        int queryRightmostZero(int ql, int qr) {
            return queryRightmostZero(1, 0, n - 1, ql, qr);
        }
        
        int queryRightmostZero(int node, int nl, int nr, int ql, int qr) {
            if (ql > nr || qr < nl) return -1;
            if (mn[node] > 0 || mx[node] < 0) return -1; // no zero in this segment
            if (nl == nr) {
                return (mn[node] == 0) ? nl : -1;
            }
            push(node);
            int mid = (nl + nr) / 2;
            // Try right child first
            int res = queryRightmostZero(node * 2 + 1, mid + 1, nr, ql, qr);
            if (res != -1) return res;
            return queryRightmostZero(node * 2, nl, mid, ql, qr);
        }
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == longestBalanced(nums);
    }
}