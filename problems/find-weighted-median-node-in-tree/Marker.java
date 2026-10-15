import java.util.*;
class Marker {
    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
        }
        int LOG = 17;
        int[][] up = new int[n][LOG];
        long[][] upW = new long[n][LOG];
        int[] depth = new int[n];
        long[] distRoot = new long[n];
        dfs(0, -1, 0, 0, adj, up, upW, depth, distRoot);
        for (int j = 1; j < LOG; j++) {
            for (int v = 0; v < n; v++) {
                int mid = up[v][j-1];
                up[v][j] = up[mid][j-1];
                upW[v][j] = upW[v][j-1] + upW[mid][j-1];
            }
        }
        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0], v = queries[qi][1];
            int l = lca(u, v, up, depth);
            long tot = distRoot[u] + distRoot[v] - 2 * distRoot[l];
            long half = (tot + 1) / 2;
            ans[qi] = findMedian(u, v, l, half, up, upW, depth, distRoot);
        }
        return ans;
    }
    void dfs(int v, int p, int d, long dist, List<int[]>[] adj, int[][] up, long[][] upW, int[] depth, long[] distRoot) {
        up[v][0] = p == -1 ? v : p;
        upW[v][0] = p == -1 ? 0 : dist - distRoot[p];
        depth[v] = d;
        distRoot[v] = dist;
        for (int[] e : adj[v]) {
            int to = e[0], w = e[1];
            if (to == p) continue;
            dfs(to, v, d + 1, dist + w, adj, up, upW, depth, distRoot);
        }
    }
    int lca(int u, int v, int[][] up, int[] depth) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        int diff = depth[u] - depth[v];
        for (int j = 0; diff > 0; j++) {
            if ((diff & 1) == 1) u = up[u][j];
            diff >>= 1;
        }
        if (u == v) return u;
        for (int j = up[0].length - 1; j >= 0; j--) {
            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }
        return up[u][0];
    }
    int findMedian(int u, int v, int l, long half, int[][] up, long[][] upW, int[] depth, long[] distRoot) {
        long sum = 0;
        int cur = u;
        for (int j = up[0].length - 1; j >= 0; j--) {
            if (depth[up[cur][j]] >= depth[l]) {
                if (sum + upW[cur][j] < half) {
                    sum += upW[cur][j];
                    cur = up[cur][j];
                }
            }
        }
        if (cur != l) return up[cur][0];
        long need = half - sum;
        long distLV = distRoot[v] - distRoot[l];
        long need2 = distLV - need;
        cur = v;
        long distToV = 0;
        for (int j = up[0].length - 1; j >= 0; j--) {
            if (depth[up[cur][j]] > depth[l]) {
                if (distToV + upW[cur][j] <= need2) {
                    distToV += upW[cur][j];
                    cur = up[cur][j];
                }
            }
        }
        return cur;
    }
    public boolean isCorrect(int n, int[][] edges, int[][] queries, int[] output) {
        return Arrays.equals(findMedian(n, edges, queries), output);
    }
}