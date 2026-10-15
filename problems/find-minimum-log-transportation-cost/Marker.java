import java.util.*;
class Marker {
    public long minCuttingCost(int n, int m, int k) {
        if (n <= k && m <= k) return 0;
        if (n > k && m > k) return -1; // impossible per constraints
        int large = Math.max(n, m);
        // cut large into 1 and large-1
        return 1L * (large - 1);
    }

    public boolean isCorrect(int n, int m, int k, long output) {
        return minCuttingCost(n, m, k) == output;
    }
}