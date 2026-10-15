import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int maxDepth = 0;
        boolean[] vis = new boolean[n + 1];
        vis[1] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 0});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            maxDepth = Math.max(maxDepth, cur[1]);
            for (int nb : adj.get(cur[0])) {
                if (!vis[nb]) {
                    vis[nb] = true;
                    q.add(new int[]{nb, cur[1] + 1});
                }
            }
        }
        long res = 1;
        for (int i = 0; i < maxDepth - 1; i++) res = res * 2 % MOD;
        return (int) res;
    }

    public boolean isCorrect(int[][] edges, int output) {
        return output == assignEdgeWeights(edges);
    }
}
