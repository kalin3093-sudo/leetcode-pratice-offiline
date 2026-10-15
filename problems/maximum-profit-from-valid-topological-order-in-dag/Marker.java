import java.util.*;

class Marker {
    public int maxProfit(int n, int[][] edges, int[] score) {
        int[] pre = new int[n];
        for (int[] e : edges) {
            pre[e[1]] |= 1 << e[0];
        }
        
        int full = 1 << n;
        int[] dp = new int[full];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        
        for (int mask = 0; mask < full; mask++) {
            if (dp[mask] == Integer.MIN_VALUE) continue;
            int k = Integer.bitCount(mask) + 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) continue;
                if ((pre[i] & mask) == pre[i]) {
                    int nmask = mask | (1 << i);
                    dp[nmask] = Math.max(dp[nmask], dp[mask] + score[i] * k);
                }
            }
        }
        
        return dp[full - 1];
    }

    public boolean isCorrect(int n, int[][] edges, int[] score, int output) {
        return output == maxProfit(n, edges, score);
    }
}