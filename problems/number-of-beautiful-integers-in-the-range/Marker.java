import java.util.*;

class Marker {
    private String s;
    private int k;
    private long[][][][][] memo;
    private boolean[][][][][] vis;

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        return (int) (countUpTo(high, k) - countUpTo(low - 1, k));
    }

    private long countUpTo(int x, int kk) {
        if (x < 1) return 0;
        s = Integer.toString(x);
        k = kk;
        int n = s.length();
        memo = new long[n][25][k][2][2];
        vis = new boolean[n][25][k][2][2];
        return dfs(0, 12, 0, 0, 1);
    }

    private long dfs(int pos, int diff, int rem, int started, int tight) {
        if (pos == s.length()) {
            return (started == 1 && diff == 12 && rem == 0) ? 1 : 0;
        }
        if (vis[pos][diff][rem][started][tight]) return memo[pos][diff][rem][started][tight];
        vis[pos][diff][rem][started][tight] = true;
        int hi = tight == 1 ? s.charAt(pos) - '0' : 9;
        long res = 0;
        for (int d = 0; d <= hi; d++) {
            int nt = (tight == 1 && d == hi) ? 1 : 0;
            int ns = (started == 1 || d > 0) ? 1 : 0;
            int nrem = ns == 1 ? (rem * 10 + d) % k : 0;
            int ndiff = ns == 1 ? diff + (d % 2 == 0 ? 1 : -1) : diff;
            res += dfs(pos + 1, ndiff, nrem, ns, nt);
        }
        memo[pos][diff][rem][started][tight] = res;
        return res;
    }

    public boolean isCorrect(int low, int high, int k, int output) {
        return output == numberOfBeautifulIntegers(low, high, k);
    }
}
