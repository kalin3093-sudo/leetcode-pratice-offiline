import java.util.*;

class Marker {
    public long interactionCosts(int n, int[][] edges, int[] group) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        parent[0] = -2;
        int[] order = new int[n];
        int tail = 0;
        order[tail++] = 0;
        for (int head = 0; head < tail; head++) {
            int u = order[head];
            for (int v : adj[u]) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    order[tail++] = v;
                }
            }
        }
        int[][] cnt = new int[n][21];
        for (int idx = n - 1; idx >= 0; idx--) {
            int u = order[idx];
            cnt[u][group[u]] = 1;
            for (int v : adj[u]) {
                if (parent[v] == u) {
                    for (int g = 1; g <= 20; g++) cnt[u][g] += cnt[v][g];
                }
            }
        }
        long ans = 0;
        for (int idx = 1; idx < n; idx++) {
            int v = order[idx];
            int[] c = cnt[v];
            int[] p = cnt[0];
            for (int g = 1; g <= 20; g++) ans += (long) c[g] * (p[g] - c[g]);
        }
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, int[] group, long output) {
        return output == interactionCosts(n, edges, group);
    }
}
