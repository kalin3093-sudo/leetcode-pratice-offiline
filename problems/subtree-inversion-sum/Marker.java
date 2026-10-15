import java.util.*;
class Marker {
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] order = new int[n];
        int ord = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(0);
        while (!st.isEmpty()) {
            int u = st.pop();
            order[ord++] = u;
            for (int v : g[u]) {
                if (v != parent[u]) {
                    parent[v] = u;
                    st.push(v);
                }
            }
        }
        int w = k + 1;
        long[][] dp = new long[n][2 * w];
        for (int idx = n - 1; idx >= 0; idx--) {
            int u = order[idx];
            for (int par = 0; par < 2; par++) {
                for (int d = 0; d <= k; d++) {
                    long eff = (par == 0 ? nums[u] : -(long) nums[u]);
                    long best = eff;
                    for (int v : g[u]) {
                        if (v == parent[u]) continue;
                        best += dp[v][par * w + Math.min(d + 1, k)];
                    }
                    if (d == k) {
                        long cand = -eff;
                        for (int v : g[u]) {
                            if (v == parent[u]) continue;
                            cand += dp[v][(1 - par) * w + 1];
                        }
                        if (cand > best) best = cand;
                    }
                    dp[u][par * w + d] = best;
                }
            }
        }
        return dp[0][k];
    }
    public boolean isCorrect(int[][] edges, int[] nums, int k, long output) {
        return output == subtreeInversionSum(edges, nums, k);
    }
}
