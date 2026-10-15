import java.util.*;
class Marker {
    public int maxWeight(int n, int[][] edges, int k, int t) {
        List<int[]>[] adj = new List[n];
        int[] indeg = new int[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            indeg[e[1]]++;
        }
        
        // Topological sort
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) q.offer(i);
        }
        List<Integer> topo = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] vw : adj[u]) {
                indeg[vw[0]]--;
                if (indeg[vw[0]] == 0) q.offer(vw[0]);
            }
        }
        
        // dp[i][j] = set of possible path weights ending at node i with j edges
        // Use boolean array since weights are small (t <= 600, k <= 300)
        boolean[][][] dp = new boolean[n][k + 1][t];
        for (int u : topo) {
            dp[u][0][0] = true; // path of 0 edges has weight 0
        }
        
        for (int u : topo) {
            for (int[] vw : adj[u]) {
                int v = vw[0];
                int w = vw[1];
                for (int j = 0; j < k; j++) {
                    for (int sum = 0; sum < t; sum++) {
                        if (dp[u][j][sum] && sum + w < t) {
                            dp[v][j + 1][sum + w] = true;
                        }
                    }
                }
            }
        }
        
        int ans = -1;
        for (int u = 0; u < n; u++) {
            for (int sum = t - 1; sum >= 0; sum--) {
                if (dp[u][k][sum]) {
                    ans = Math.max(ans, sum);
                    break;
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, int k, int t, int output) {
        int expected = maxWeight(n, edges, k, t);
        return expected == output;
    }
}