import java.util.*;

class Marker {
    public int minOperations(String s, int k) {
        int n = s.length();
        int zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        if (zeros == 0) return 0;
        
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Deque<Integer> q = new ArrayDeque<>();
        dist[zeros] = 0;
        q.add(zeros);
        
        TreeSet<Integer> even = new TreeSet<>();
        TreeSet<Integer> odd = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            if (i != zeros) {
                if ((i & 1) == 0) even.add(i);
                else odd.add(i);
            }
        }
        
        while (!q.isEmpty()) {
            int z = q.poll();
            int d = dist[z];
            
            if (z == 0) return d;
            
            int maxOnes = Math.min(k, n - z);
            int minOnes = Math.max(0, k - z);
            int minNewZ = z + k - 2 * maxOnes;
            int maxNewZ = z + k - 2 * minOnes;
            
            // Clamp to valid range
            if (minNewZ < 0) minNewZ = 0;
            if (maxNewZ > n) maxNewZ = n;
            if (minNewZ > maxNewZ) continue;
            
            int parity = (z + k) & 1;
            TreeSet<Integer> set = (parity == 0) ? even : odd;
            
            // Adjust minNewZ to have correct parity
            if ((minNewZ & 1) != parity) minNewZ++;
            if (minNewZ > maxNewZ) continue;
            
            Integer next = set.ceiling(minNewZ);
            while (next != null && next <= maxNewZ) {
                dist[next] = d + 1;
                q.add(next);
                set.remove(next);
                next = set.ceiling(minNewZ);
            }
        }
        return -1;
    }

    public boolean isCorrect(String s, int k, int output) {
        return minOperations(s, k) == output;
    }
}