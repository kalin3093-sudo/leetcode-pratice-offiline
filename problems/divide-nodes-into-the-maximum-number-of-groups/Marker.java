import java.util.*;
class Marker {
    public int magnificentSets(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);
        int ans = 0;
        boolean[] vis = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (vis[i]) continue;
            List<Integer> comp = new ArrayList<>();
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            color[i] = 0;
            vis[i] = true;
            comp.add(i);
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : g[u]) {
                    if (color[v] == -1) {
                        color[v] = color[u] ^ 1;
                        vis[v] = true;
                        q.add(v);
                        comp.add(v);
                    } else if (color[v] == color[u]) {
                        return -1;
                    }
                }
            }
            int best = 0;
            for (int start : comp) {
                int[] dist = new int[n + 1];
                Arrays.fill(dist, -1);
                Queue<Integer> qq = new ArrayDeque<>();
                qq.add(start);
                dist[start] = 0;
                int maxD = 0;
                while (!qq.isEmpty()) {
                    int u = qq.poll();
                    maxD = Math.max(maxD, dist[u]);
                    for (int v : g[u]) {
                        if (dist[v] == -1) {
                            dist[v] = dist[u] + 1;
                            qq.add(v);
                        }
                    }
                }
                best = Math.max(best, maxD + 1);
            }
            ans += best;
        }
        return ans;
    }

    public boolean isCorrect(int n, int[][] edges, int output) {
        return magnificentSets(n, edges) == output;
    }
}