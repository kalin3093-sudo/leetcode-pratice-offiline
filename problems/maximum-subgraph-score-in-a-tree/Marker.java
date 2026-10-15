import java.util.*;
class Marker {
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        int[] val = new int[n];
        for (int i = 0; i < n; i++) val[i] = good[i] == 1 ? 1 : -1;
        
        int[] dp = new int[n];
        int[] ans = new int[n];
        
        dfs1(0, -1, adj, val, dp);
        dfs2(0, -1, 0, adj, val, dp, ans);
        
        return ans;
    }
    
    private void dfs1(int u, int p, List<Integer>[] adj, int[] val, int[] dp) {
        dp[u] = val[u];
        for (int v : adj[u]) {
            if (v == p) continue;
            dfs1(v, u, adj, val, dp);
            if (dp[v] > 0) dp[u] += dp[v];
        }
    }
    
    private void dfs2(int u, int p, int fromParent, List<Integer>[] adj, int[] val, int[] dp, int[] ans) {
        ans[u] = val[u] + (fromParent > 0 ? fromParent : 0);
        for (int v : adj[u]) {
            if (v == p) continue;
            if (dp[v] > 0) ans[u] += dp[v];
        }
        
        for (int v : adj[u]) {
            if (v == p) continue;
            int withoutV = val[u] + (fromParent > 0 ? fromParent : 0);
            for (int w : adj[u]) {
                if (w == p || w == v) continue;
                if (dp[w] > 0) withoutV += dp[w];
            }
            int newFromParent = withoutV > 0 ? withoutV : 0;
            dfs2(v, u, newFromParent, adj, val, dp, ans);
        }
    }

    public boolean isCorrect(int n, int[][] edges, int[] good, int[] output) {
        int[] expected = maxSubgraphScore(n, edges, good);
        return Arrays.equals(expected, output);
    }
}