import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long[] up = new long[m], down = new long[m];
        for (int v = 0; v < m; v++) {
            up[v] = v;
            down[v] = m - 1 - v;
        }
        for (int len = 3; len <= n; len++) {
            long[] nu = new long[m], nd = new long[m];
            long run = 0;
            for (int y = 0; y < m; y++) {
                nu[y] = run;
                run = (run + down[y]) % MOD;
            }
            run = 0;
            for (int y = m - 1; y >= 0; y--) {
                nd[y] = run;
                run = (run + up[y]) % MOD;
            }
            up = nu;
            down = nd;
        }
        long total = 0;
        for (int v = 0; v < m; v++) total = (total + up[v] + down[v]) % MOD;
        return (int) total;
    }

    public boolean isCorrect(int n, int l, int r, int output) {
        return output == zigZagArrays(n, l, r);
    }
}
