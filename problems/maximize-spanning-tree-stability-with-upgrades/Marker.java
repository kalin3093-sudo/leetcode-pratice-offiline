import java.util.*;
class Marker {
    public int maxStability(int n, int[][] edges, int k) {
        List<int[]> mandatory = new ArrayList<>();
        List<int[]> optional = new ArrayList<>();
        for (int[] e : edges) {
            if (e[3] == 1) mandatory.add(e);
            else optional.add(e);
        }
        
        if (hasCycle(n, mandatory)) return -1;
        
        int lo = 1, hi = 2_000_000, ans = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (check(n, mandatory, optional, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean check(int n, List<int[]> mandatory, List<int[]> optional, int k, int target) {
        DSU dsu = new DSU(n);
        int upgrades = k;
        
        for (int[] e : mandatory) {
            int u = e[0], v = e[1], w = e[2];
            if (w < target) return false;
            dsu.union(u, v);
        }
        
        List<int[]> opt = new ArrayList<>();
        for (int[] e : optional) {
            int u = e[0], v = e[1], w = e[2];
            if (dsu.find(u) != dsu.find(v)) {
                opt.add(e);
            }
        }
        
        opt.sort((a, b) -> Integer.compare(b[2], a[2]));
        
        for (int[] e : opt) {
            int u = e[0], v = e[1], w = e[2];
            if (dsu.find(u) == dsu.find(v)) continue;
            if (w >= target) {
                dsu.union(u, v);
            } else if (upgrades > 0 && 2 * w >= target) {
                dsu.union(u, v);
                upgrades--;
            }
        }
        
        return dsu.count == 1;
    }
    
    private boolean hasCycle(int n, List<int[]> edges) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            if (!dsu.union(e[0], e[1])) return true;
        }
        return false;
    }
    
    static class DSU {
        int[] p, sz;
        int count;
        DSU(int n) {
            p = new int[n];
            sz = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
                sz[i] = 1;
            }
        }
        int find(int x) {
            return p[x] == x ? x : (p[x] = find(p[x]));
        }
        boolean union(int a, int b) {
            a = find(a); b = find(b);
            if (a == b) return false;
            if (sz[a] < sz[b]) { int t = a; a = b; b = t; }
            p[b] = a;
            sz[a] += sz[b];
            count--;
            return true;
        }
    }

    public boolean isCorrect(int n, int[][] edges, int k, int output) {
        return maxStability(n, edges, k) == output;
    }
}