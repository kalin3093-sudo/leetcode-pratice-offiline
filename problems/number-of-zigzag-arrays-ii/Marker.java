import java.util.*;

class Marker {
    static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) return m % MOD;
        int sz = 2 * m;
        int[][] t = new int[sz][sz];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < x; y++) t[m + y][x] = 1;
            for (int y = x + 1; y < m; y++) t[y][m + x] = 1;
        }
        int[][] res = new int[sz][sz];
        for (int i = 0; i < sz; i++) res[i][i] = 1;
        int e = n - 2;
        int[][] base = t;
        while (e > 0) {
            if ((e & 1) == 1) res = mul(res, base);
            base = mul(base, base);
            e >>= 1;
        }
        long[] vec = new long[sz];
        for (int v = 0; v < m; v++) {
            vec[v] = v;
            vec[m + v] = m - 1 - v;
        }
        long[] out = new long[sz];
        for (int i = 0; i < sz; i++)
            for (int j = 0; j < sz; j++)
                out[i] = (out[i] + (long) res[i][j] * vec[j]) % MOD;
        long total = 0;
        for (long v : out) total += v;
        return (int) (total % MOD);
    }

    private int[][] mul(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++)
                    c[i][j] = (int) ((c[i][j] + (long) a[i][k] * b[k][j]) % MOD);
            }
        return c;
    }

    public boolean isCorrect(int n, int l, int r, int output) {
        return output == zigZagArrays(n, l, r);
    }
}
