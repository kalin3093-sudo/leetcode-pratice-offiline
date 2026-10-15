import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int log = 1;
        while ((1 << log) <= n) log++;
        int[][] up = new int[n + 1][log];
        int[] depth = new int[n + 1];
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int nb : adj.get(u)) {
                if (!vis[nb]) {
                    vis[nb] = true;
                    depth[nb] = depth[u] + 1;
                    up[nb][0] = u;
                    for (int k = 1; k < log; k++)
                        up[nb][k] = up[up[nb][k - 1]][k - 1];
                    q.add(nb);
                }
            }
        }
        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) pow2[i] = pow2[i - 1] * 2 % MOD;
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            int a = u, b = v;
            if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
            int diff = depth[a] - depth[b];
            for (int k = 0; k < log; k++)
                if (((diff >> k) & 1) == 1) a = up[a][k];
            if (a != b) {
                for (int k = log - 1; k >= 0; k--) {
                    if (up[a][k] != up[b][k]) {
                        a = up[a][k];
                        b = up[b][k];
                    }
                }
                a = up[a][0];
            }
            int d = depth[u] + depth[v] - 2 * depth[a];
            ans[i] = d == 0 ? 0 : (int) pow2[d - 1];
        }
        return ans;
    }

    public boolean isCorrect(int[][] edges, int[][] queries, int[] output) {
        int[] expected = assignEdgeWeights(edges, queries);
        if (output == null || output.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++)
            if (output[i] != expected[i]) return false;
        return true;
    }
}
