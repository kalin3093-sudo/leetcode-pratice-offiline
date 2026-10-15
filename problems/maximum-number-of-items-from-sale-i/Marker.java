import java.util.*;

class Marker {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        for (int[] it : items) maxFactor = Math.max(maxFactor, it[0]);
        
        int[] cnt = new int[maxFactor + 1];
        for (int[] it : items) cnt[it[0]]++;
        
        int[] divCnt = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            for (int m = f; m <= maxFactor; m += f) {
                divCnt[f] += cnt[m];
            }
        }
        
        int[] gain = new int[n];
        for (int i = 0; i < n; i++) {
            gain[i] = divCnt[items[i][0]] - 1;
        }
        
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int g = gain[i];
            int[] ndp = dp.clone();
            for (int b = price; b <= budget; b++) {
                if (dp[b - price] >= 0) {
                    int val = 1 + g;
                    ndp[b] = Math.max(ndp[b], dp[b - price] + val);
                }
            }
            for (int b = 2 * price; b <= budget; b++) {
                if (ndp[b - price] >= 0) {
                    ndp[b] = Math.max(ndp[b], ndp[b - price] + 1);
                }
            }
            dp = ndp;
        }
        
        int ans = 0;
        for (int v : dp) ans = Math.max(ans, v);
        return ans;
    }

    public boolean isCorrect(int[][] items, int budget, int output) {
        return output == maximumSaleItems(items, budget);
    }
}