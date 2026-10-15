import java.util.*;
class Marker {
    public long minCost(String s, int encCost, int flatCost) {
        int n = s.length();
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
        Map<Long, Long> memo = new HashMap<>();
        return dfs(0, n, pre, encCost, flatCost, memo);
    }

    private long dfs(int l, int r, int[] pre, int encCost, int flatCost, Map<Long, Long> memo) {
        long key = ((long)l << 32) | r;
        if (memo.containsKey(key)) return memo.get(key);

        int L = r - l;
        int X = pre[r] - pre[l];
        long costNoSplit = (X == 0) ? flatCost : (long)L * X * encCost;
        long best = costNoSplit;

        if (L % 2 == 0) {
            int mid = (l + r) / 2;
            long costSplit = dfs(l, mid, pre, encCost, flatCost, memo)
                           + dfs(mid, r, pre, encCost, flatCost, memo);
            best = Math.min(best, costSplit);
        }
        memo.put(key, best);
        return best;
    }

    public boolean isCorrect(String s, int encCost, int flatCost, long output) {
        long expected = minCost(s, encCost, flatCost);
        return expected == output;
    }
}