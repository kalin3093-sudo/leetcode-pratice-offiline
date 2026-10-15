import java.util.*;
class Marker {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        boolean[] isBanned = new boolean[n];
        for (int b : banned) isBanned[b] = true;
        
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[p] = 0;
        
        // Two sets for even and odd positions
        TreeSet<Integer>[] unvisited = new TreeSet[2];
        unvisited[0] = new TreeSet<>();
        unvisited[1] = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (i != p && !isBanned[i]) {
                unvisited[i % 2].add(i);
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(p);
        
        while (!q.isEmpty()) {
            int u = q.poll();
            int d = dist[u];
            
            // Find the range of positions v that can reach u by reversing a subarray of size k
            // v is the position of 1 before the reverse operation
            // After reversing [v, v+k-1], 1 moves from v to v+k-1
            // Or 1 moves from v+k-1 to v
            // So if 1 is at u after reverse, it was at v = u - k + 1 before (if u is right end)
            // Or it was at v = u before (if u is left end)
            
            // The reverse operation on subarray [L, L+k-1] maps position x to L + (L+k-1) - x = 2L + k - 1 - x
            // For x = u (current position), the previous position v = 2L + k - 1 - u
            // where L is the left end of the subarray, and L <= u <= L+k-1
            // Also v must be in [L, L+k-1] and not banned
            
            // L ranges from max(0, u-k+1) to min(u, n-k)
            int minL = Math.max(0, u - k + 1);
            int maxL = Math.min(u, n - k);
            
            // v = 2L + k - 1 - u
            // As L increases by 1, v increases by 2
            // So v has same parity as (k - 1 - u)
            int startV = 2 * minL + k - 1 - u;
            int endV = 2 * maxL + k - 1 - u;
            
            // v must be in [0, n-1], not banned, and not visited
            // We need to find all unvisited v in [startV, endV] with step 2
            // Since step is 2, all v have the same parity
            int parity = (startV % 2 + 2) % 2;
            
            // Find the actual range within [0, n-1]
            int lo = Math.max(0, startV);
            int hi = Math.min(n - 1, endV);
            
            // Adjust lo to have correct parity
            if ((lo % 2 + 2) % 2 != parity) lo++;
            
            if (lo <= hi) {
                TreeSet<Integer> set = unvisited[parity];
                Integer v = set.ceiling(lo);
                while (v != null && v <= hi) {
                    if (!isBanned[v]) {
                        dist[v] = d + 1;
                        q.offer(v);
                        set.remove(v);
                    }
                    v = set.ceiling(v + 2);
                }
            }
        }
        
        return dist;
    }

    public boolean isCorrect(int n, int p, int[] banned, int k, int[] output) {
        int[] expected = minReverseOperations(n, p, banned, k);
        return Arrays.equals(expected, output);
    }
}