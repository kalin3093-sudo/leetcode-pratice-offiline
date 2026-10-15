import java.util.*;
class Marker {
    public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
        if (source == target) return 0;
        
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int maxW = 0;
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            adj[e[1]].add(new int[]{e[0], e[2]});
            maxW = Math.max(maxW, e[2]);
        }
        
        int left = 0, right = maxW;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(n, adj, source, target, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    private boolean canReach(int n, List<int[]>[] adj, int source, int target, int k, int threshold) {
        // 0-1 BFS: light edges cost 0, heavy edges cost 1
        int[] dist = new int[n];
        Arrays.fill(dist, k + 1);
        Deque<Integer> dq = new ArrayDeque<>();
        dist[source] = 0;
        dq.offer(source);
        
        while (!dq.isEmpty()) {
            int u = dq.poll();
            if (u == target) return dist[u] <= k;
            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                int cost = w > threshold ? 1 : 0;
                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                    if (cost == 0) dq.offerFirst(v);
                    else dq.offerLast(v);
                }
            }
        }
        return dist[target] <= k;
    }

    public boolean isCorrect(int n, int[][] edges, int source, int target, int k, int output) {
        return minimumThreshold(n, edges, source, target, k) == output;
    }
}