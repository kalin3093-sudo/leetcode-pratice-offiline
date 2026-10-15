import java.util.*;
class Marker {
    private static final int MOD = 1000000007;

    public int[] waysToFillArray(int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int n = queries[i][0], k = queries[i][1];
            long ways = 1;
            for (int p = 2; p * p <= k; p++) {
                int e = 0;
                while (k % p == 0) { k /= p; e++; }
                if (e > 0) ways = ways * starsBars(n, e) % MOD;
            }
            if (k > 1) ways = ways * starsBars(n, 1) % MOD;
            res[i] = (int) ways;
        }
        return res;
    }

    // C(n + e - 1, e) mod MOD; e <= 13 for k <= 1e4
    private long starsBars(int n, int e) {
        long res = 1;
        for (int j = 1; j <= e; j++) {
            res = res * (n - 1 + j) % MOD;
            res = res * modInverse(j) % MOD;
        }
        return res;
    }

    private long modInverse(long a) {
        long res = 1, e = MOD - 2;
        while (e > 0) {
            if ((e & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }

    public boolean isCorrect(int[][] queries, int[] output) {
        int[] expected = waysToFillArray(queries);
        if (output == null || output.length != expected.length) return false;
        for (int i = 0; i < expected.length; i++) {
            if (output[i] != expected[i]) return false;
        }
        return true;
    }
}
