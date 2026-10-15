import java.util.*;
class Marker {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }
        int[] dx = bfs(adj, n, x), dy = bfs(adj, n, y), dz = bfs(adj, n, z);
        int count = 0;
        for (int u = 0; u < n; u++) {
            int[] d = { dx[u], dy[u], dz[u] };
            Arrays.sort(d);
            long a = d[0], b = d[1], cc = d[2];
            if (a * a + b * b == cc * cc) count++;
        }
        return count;
    }

    private int[] bfs(List<List<Integer>> adj, int n, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) if (dist[v] < 0) { dist[v] = dist[u] + 1; q.add(v); }
        }
        return dist;
    }

    public boolean isCorrect(int n, int[][] edges, int x, int y, int z, int output) {
        return specialNodes(n, edges, x, y, z) == output;
    }
}
