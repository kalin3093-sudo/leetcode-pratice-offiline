import java.util.*;

class Marker {
    public int maxPartitionFactor(int[][] points) {
        int n = points.length;
        if (n == 2) return 0;
        
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dist[i][j] = dist[j][i] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }
        
        int lo = 1, hi = 0;
        for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) hi = Math.max(hi, dist[i][j]);
        
        int ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canPartition(dist, n, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean canPartition(int[][] dist, int n, int D) {
        int[] color = new int[n];
        Arrays.fill(color, -1);
        for (int i = 0; i < n; i++) {
            if (color[i] != -1) continue;
            color[i] = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.add(i);
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v = 0; v < n; v++) {
                    if (u == v) continue;
                    if (dist[u][v] < D) {
                        if (color[v] == -1) {
                            color[v] = color[u] ^ 1;
                            q.add(v);
                        } else if (color[v] == color[u]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isCorrect(int[][] points, int output) {
        return output == maxPartitionFactor(points);
    }
}