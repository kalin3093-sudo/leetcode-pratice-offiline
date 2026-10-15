import java.util.*;
class Marker {
    public int[] minCost(int n, int[] prices, int[][] roads) {
        List<int[]>[] adjEmpty = new List[n];
        List<int[]>[] adjTaxi = new List[n];
        for (int i = 0; i < n; i++) {
            adjEmpty[i] = new ArrayList<>();
            adjTaxi[i] = new ArrayList<>();
        }
        for (int[] r : roads) {
            int u = r[0], v = r[1], c = r[2], t = r[3];
            adjEmpty[u].add(new int[]{v, c});
            adjEmpty[v].add(new int[]{u, c});
            long w = (long)c * t;
            adjTaxi[u].add(new int[]{v, (int)w});
            adjTaxi[v].add(new int[]{u, (int)w});
        }

        long[][] distEmpty = new long[n][n];
        long[][] distTaxi = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distEmpty[i], Long.MAX_VALUE);
            Arrays.fill(distTaxi[i], Long.MAX_VALUE);
            dijkstra(i, adjEmpty, distEmpty[i]);
            dijkstra(i, adjTaxi, distTaxi[i]);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            long best = prices[i];
            for (int j = 0; j < n; j++) {
                if (distEmpty[i][j] != Long.MAX_VALUE && distTaxi[i][j] != Long.MAX_VALUE) {
                    best = Math.min(best, distEmpty[i][j] + prices[j] + distTaxi[i][j]);
                }
            }
            ans[i] = (int)best;
        }
        return ans;
    }

    private void dijkstra(int src, List<int[]>[] adj, long[] dist) {
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, src});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int)cur[1];
            if (d != dist[u]) continue;
            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }
    }

    public boolean isCorrect(int n, int[] prices, int[][] roads, int[] output) {
        int[] expected = minCost(n, prices, roads);
        return Arrays.equals(expected, output);
    }
}