import java.util.*;
class Marker {
    public int minTime(int n, int[][] edges, int k) {
        if (k == 1) return 0;
        
        // Count initial components
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            dsu.union(e[0], e[1]);
        }
        int initialComponents = 0;
        for (int i = 0; i < n; i++) if (dsu.find(i) == i) initialComponents++;
        if (initialComponents >= k) return 0;
        
        // Sort edges by time
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));
        
        // Process edges in order of increasing time
        dsu = new DSU(n);
        for (int[] e : edges) {
            dsu.union(e[0], e[1]);
        }
        int currentComponents = 0;
        for (int i = 0; i < n; i++) if (dsu.find(i) == i) currentComponents++;
        
        // Remove edges in increasing time order
        dsu = new DSU(n);
        for (int[] e : edges) {
            dsu.union(e[0], e[1]);
        }
        
        // Actually, we need to simulate removing edges with time <= t
        // Sort edges by time
        // For a given t, include only edges with time > t
        // Binary search on t
        int maxTime = 0;
        for (int[] e : edges) maxTime = Math.max(maxTime, e[2]);
        
        int left = 1, right = maxTime, ans = maxTime;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            DSU d = new DSU(n);
            for (int[] e : edges) {
                if (e[2] > mid) d.union(e[0], e[1]);
            }
            int comps = 0;
            for (int i = 0; i < n; i++) if (d.find(i) == i) comps++;
            if (comps >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else { parent[ry] = rx; rank[rx]++; }
        }
    }

    public boolean isCorrect(int n, int[][] edges, int k, int output) {
        return minTime(n, edges, k) == output;
    }
}