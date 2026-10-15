import java.util.*;
class Marker {
    public long maxScore(int n, int[][] edges) {
        if (n <= 1) return 0;
        int[] a = new int[n];
        int idx = 0;
        if (edges.length == n) {
            // cycle: 1, ascending evens, descending odds
            a[idx++] = 1;
            for (int v = 2; v <= n; v += 2) a[idx++] = v;
            for (int v = (n % 2 == 1) ? n : n - 1; v >= 3; v -= 2) a[idx++] = v;
            long sum = 0;
            for (int i = 0; i < n; i++) sum += (long) a[i] * a[(i + 1) % n];
            return sum;
        }
        // path: ascending odds, descending evens
        for (int v = 1; v <= n; v += 2) a[idx++] = v;
        for (int v = (n % 2 == 0) ? n : n - 1; v >= 2; v -= 2) a[idx++] = v;
        long sum = 0;
        for (int i = 0; i + 1 < n; i++) sum += (long) a[i] * a[i + 1];
        return sum;
    }

    public boolean isCorrect(int n, int[][] edges, long output) {
        return maxScore(n, edges) == output;
    }
}
