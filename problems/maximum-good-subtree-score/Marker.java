import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;
    
    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = vals.length;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) children[par[i]].add(i);
        
        int[] nodeMask = new int[n];
        for (int i = 0; i < n; i++) {
            int v = vals[i];
            int mask = 0;
            boolean ok = true;
            while (v > 0) {
                int d = v % 10;
                if ((mask & (1 << d)) != 0) {
                    ok = false;
                    break;
                }
                mask |= 1 << d;
                v /= 10;
            }
            nodeMask[i] = ok ? mask : -1;
        }
        
        long ans = 0;
        Map<Integer, Integer>[] dp = new Map[n];
        dfs(0, children, vals, nodeMask, dp);
        for (int i = 0; i < n; i++) {
            int maxScore = 0;
            for (int score : dp[i].values()) {
                maxScore = Math.max(maxScore, score);
            }
            ans = (ans + maxScore) % MOD;
        }
        return (int) ans;
    }
    
    private void dfs(int u, List<Integer>[] children, int[] vals, int[] nodeMask, Map<Integer, Integer>[] dp) {
        Map<Integer, Integer> cur = new HashMap<>();
        if (nodeMask[u] != -1) {
            cur.put(nodeMask[u], vals[u]);
        }
        cur.put(0, 0);
        
        for (int v : children[u]) {
            dfs(v, children, vals, nodeMask, dp);
            Map<Integer, Integer> child = dp[v];
            Map<Integer, Integer> merged = new HashMap<>(cur);
            for (Map.Entry<Integer, Integer> e1 : cur.entrySet()) {
                int m1 = e1.getKey(), s1 = e1.getValue();
                for (Map.Entry<Integer, Integer> e2 : child.entrySet()) {
                    int m2 = e2.getKey(), s2 = e2.getValue();
                    if ((m1 & m2) == 0) {
                        int nm = m1 | m2;
                        int ns = s1 + s2;
                        merged.put(nm, Math.max(merged.getOrDefault(nm, 0), ns));
                    }
                }
            }
            cur = merged;
        }
        dp[u] = cur;
    }

    public boolean isCorrect(int[] vals, int[] par, int output) {
        return output == goodSubtreeSum(vals, par);
    }
}