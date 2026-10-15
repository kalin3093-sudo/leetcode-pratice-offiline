import java.util.*;

class Marker {
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        List<Integer>[] children = new List[n + 1];
        for (int i = 1; i <= n; i++) children[i] = new ArrayList<>();
        for (int[] e : hierarchy) {
            children[e[0]].add(e[1]);
        }
        
        int[][] dp0 = new int[n + 1][budget + 1];
        int[][] dp1 = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp0[i], -1);
            Arrays.fill(dp1[i], -1);
        }
        
        dfs(1, children, present, future, dp0, dp1, budget);
        
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp0[1][b]);
        }
        return ans;
    }
    
    private void dfs(int u, List<Integer>[] children, int[] present, int[] future, 
                     int[][] dp0, int[][] dp1, int budget) {
        int p = present[u - 1];
        int f = future[u - 1];
        int profit = f - p;
        int pHalf = p / 2;
        int profitHalf = f - pHalf;
        
        // First, compute children's dp arrays
        for (int v : children[u]) {
            dfs(v, children, present, future, dp0, dp1, budget);
        }
        
        // Merge children for case where u doesn't buy (children use dp0)
        int[] curNoBuy = new int[budget + 1];
        Arrays.fill(curNoBuy, -1);
        curNoBuy[0] = 0;
        for (int v : children[u]) {
            int[] nxt = new int[budget + 1];
            Arrays.fill(nxt, -1);
            for (int b = 0; b <= budget; b++) {
                if (curNoBuy[b] < 0) continue;
                for (int c = 0; b + c <= budget; c++) {
                    if (dp0[v][c] >= 0) {
                        nxt[b + c] = Math.max(nxt[b + c], curNoBuy[b] + dp0[v][c]);
                    }
                }
            }
            curNoBuy = nxt;
        }
        
        // Merge children for case where u buys (children use dp1)
        int[] curBuy = new int[budget + 1];
        Arrays.fill(curBuy, -1);
        curBuy[0] = 0;
        for (int v : children[u]) {
            int[] nxt = new int[budget + 1];
            Arrays.fill(nxt, -1);
            for (int b = 0; b <= budget; b++) {
                if (curBuy[b] < 0) continue;
                for (int c = 0; b + c <= budget; c++) {
                    if (dp1[v][c] >= 0) {
                        nxt[b + c] = Math.max(nxt[b + c], curBuy[b] + dp1[v][c]);
                    }
                }
            }
            curBuy = nxt;
        }
        
        // dp0[u]: parent didn't buy
        for (int b = 0; b <= budget; b++) {
            dp0[u][b] = curNoBuy[b];
        }
        if (p <= budget) {
            for (int b = p; b <= budget; b++) {
                if (curBuy[b - p] >= 0) {
                    dp0[u][b] = Math.max(dp0[u][b], curBuy[b - p] + profit);
                }
            }
        }
        
        // dp1[u]: parent bought
        for (int b = 0; b <= budget; b++) {
            dp1[u][b] = curNoBuy[b];
        }
        if (pHalf <= budget) {
            for (int b = pHalf; b <= budget; b++) {
                if (curBuy[b - pHalf] >= 0) {
                    dp1[u][b] = Math.max(dp1[u][b], curBuy[b - pHalf] + profitHalf);
                }
            }
        }
    }

    public boolean isCorrect(int n, int[] present, int[] future, int[][] hierarchy, int budget, int output) {
        return output == maxProfit(n, present, future, hierarchy, budget);
    }
}