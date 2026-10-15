import java.util.*;

class Marker {
    private String s;
    private long[][][][] memo;
    private boolean[][][][] vis;

    public long countBalanced(long low, long high) {
        return countUpTo(high) - countUpTo(low - 1);
    }

    private long countUpTo(long x) {
        if (x < 1) return 0;
        s = Long.toString(x);
        int n = s.length();
        memo = new long[n][321][3][2];
        vis = new boolean[n][321][3][2];
        return dfs(0, 160, 0, 1);
    }

    private long dfs(int pos, int diff, int placed, int tight) {
        if (pos == s.length()) {
            return (placed >= 2 && diff == 160) ? 1 : 0;
        }
        if (vis[pos][diff][placed][tight]) return memo[pos][diff][placed][tight];
        vis[pos][diff][placed][tight] = true;
        int hi = tight == 1 ? s.charAt(pos) - '0' : 9;
        long res = 0;
        for (int d = 0; d <= hi; d++) {
            int nt = (tight == 1 && d == hi) ? 1 : 0;
            if (placed == 0) {
                if (d == 0) res += dfs(pos + 1, diff, 0, nt);
                else res += dfs(pos + 1, diff + d, 1, nt);
            } else {
                int np = placed == 2 ? 2 : placed + 1;
                int nd = (placed % 2 == 1) ? diff - d : diff + d;
                res += dfs(pos + 1, nd, np, nt);
            }
        }
        memo[pos][diff][placed][tight] = res;
        return res;
    }

    public boolean isCorrect(long low, long high, long output) {
        return output == countBalanced(low, high);
    }
}
