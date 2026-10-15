import java.util.*;
class Marker {
    public int minTime(int n, int[][] edges) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e); // [u, v, start, end]
        }
        
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        
        // Priority queue: (time, node)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0});
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], u = cur[1];
            if (t > dist[u]) continue;
            if (u == n - 1) return t;
            
            for (int[] e : adj[u]) {
                int v = e[1], start = e[2], end = e[3];
                if (t > end) continue; // missed the window
                int depart = Math.max(t, start);
                int arrive = depart + 1;
                if (arrive < dist[v]) {
                    dist[v] = arrive;
                    pq.offer(new int[]{arrive, v});
                }
            }
        }
        
        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }

    public boolean isCorrect(int n, int[][] edges, int output) {
        return minTime(n, edges) == output;
    }
}