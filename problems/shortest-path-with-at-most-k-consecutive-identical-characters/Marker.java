import java.util.*;

class Marker {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int[] e : edges)
            adj[e[0]].add(new int[] { e[1], e[2] });

        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], INF);
        dist[0][1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[] { 0, 0, 1 });
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            int cnt = (int) cur[2];
            if (d != dist[u][cnt])
                continue;
            for (int[] e : adj[u]) {
                int v = e[0];
                int next = (labels.charAt(u) == labels.charAt(v)) ? cnt + 1 : 1;
                if (next > k)
                    continue;
                if (d + e[1] < dist[v][next]) {
                    dist[v][next] = d + e[1];
                    pq.add(new long[] { dist[v][next], v, next });
                }
            }
        }
        long best = INF;
        for (int c = 1; c <= k; c++)
            best = Math.min(best, dist[n - 1][c]);
        return best == INF ? -1 : (int) best;
    }

    public boolean isCorrect(int n, int[][] edges, String labels, int k, int output) {
        return shortestPath(n, edges, labels, k) == output;
    }
}
