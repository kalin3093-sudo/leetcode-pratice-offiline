import java.util.*;
class Marker {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long base = 0;
        for (int i = 0; i < n; i++) base += (long) strategy[i] * prices[i];
        long best = base;
        if (k % 2 != 0 || k > n) return best;
        int h = k / 2;
        long[] P = new long[n + 1], SP = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + prices[i];
            SP[i + 1] = SP[i] + (long) strategy[i] * prices[i];
        }
        for (int l = 0; l + k <= n; l++) {
            long delta = -(SP[l + h] - SP[l])
                    + (P[l + k] - P[l + h])
                    - (SP[l + k] - SP[l + h]);
            if (base + delta > best) best = base + delta;
        }
        return best;
    }

    public boolean isCorrect(int[] prices, int[] strategy, int k, long output) {
        return maxProfit(prices, strategy, k) == output;
    }
}
