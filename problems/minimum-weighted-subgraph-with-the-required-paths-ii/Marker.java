import java.util.*;
class Marker {
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }
        
        // Root the tree at 0, compute parent, depth, distToRoot
        int LOG = 17; // 2^17 > 10^5
        int[][] parent = new int[LOG][n];
        int[] depth = new int[n];
        int[] dist = new int[n];
        
        // DFS to set up
        dfs(0, -1, 0, 0, adj, parent[0], depth, dist);
        
        // Binary lifting
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                parent[j][i] = parent[j-1][parent[j-1][i]];
            }
        }
        
        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int a = queries[qi][0];
            int b = queries[qi][1];
            int c = queries[qi][2];
            
            long dab = distance(a, b, depth, parent, LOG, dist);
            long dbc = distance(b, c, depth, parent, LOG, dist);
            long dca = distance(c, a, depth, parent, LOG, dist);
            
            ans[qi] = (int) ((dab + dbc + dca) / 2);
        }
        return ans;
    }
    
    private void dfs(int u, int p, int d, int distU, List<int[]>[] adj, int[] parent0, int[] depth, int[] dist) {
        parent0[u] = p == -1 ? u : p;
        depth[u] = d;
        dist[u] = distU;
        for (int[] e : adj[u]) {
            int v = e[0], w = e[1];
            if (v == p) continue;
            dfs(v, u, d + 1, distU + w, adj, parent0, depth, dist);
        }
    }
    
    private int lca(int u, int v, int[] depth, int[][] parent, int LOG) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        // Lift u to same depth as v
        for (int j = LOG - 1; j >= 0; j--) {
            if (depth[u] - (1 << j) >= depth[v]) {
                u = parent[j][u];
            }
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (parent[j][u] != parent[j][v]) {
                u = parent[j][u];
                v = parent[j][v];
            }
        }
        return parent[0][u];
    }
    
    private long distance(int u, int v, int[] depth, int[][] parent, int LOG, int[] dist) {
        int w = lca(u, v, depth, parent, LOG);
        return (long) dist[u] + dist[v] - 2 * dist[w];
    }

    public boolean isCorrect(int[][] edges, int[][] queries, int[] output) {
        int[] expected = minimumWeight(edges, queries);
        return Arrays.equals(expected, output);
    }
}