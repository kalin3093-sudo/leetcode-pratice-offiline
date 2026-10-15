import java.util.*;
class Marker {
    public int maxLen(int n, int[][] edges, String label) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        int[][][] dp = new int[1 << n][n][n];
        for (int[][] layer : dp) for (int[] row : layer) Arrays.fill(row, 0);
        
        int ans = 1;
        for (int i = 0; i < n; i++) {
            dp[1 << i][i][i] = 1;
        }
        
        for (int u = 0; u < n; u++) {
            for (int v : adj[u]) {
                if (u < v && label.charAt(u) == label.charAt(v)) {
                    int mask = (1 << u) | (1 << v);
                    dp[mask][u][v] = dp[mask][v][u] = 2;
                    ans = Math.max(ans, 2);
                }
            }
        }
        
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0) continue;
                    int cur = dp[mask][u][v];
                    if (cur == 0) continue;
                    ans = Math.max(ans, cur);
                    
                    for (int x : adj[u]) {
                        if ((mask & (1 << x)) != 0) continue;
                        for (int y : adj[v]) {
                            if ((mask & (1 << y)) != 0) continue;
                            if (x == y) continue;
                            if (label.charAt(x) == label.charAt(y)) {
                                int nmask = mask | (1 << x) | (1 << y);
                                dp[nmask][x][y] = Math.max(dp[nmask][x][y], cur + 2);
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, String label, int output) {
        return maxLen(n, edges, label) == output;
    }
}