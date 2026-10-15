import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countValidSequences(int n, int k) {
        if (n < k) return 0;
        int maxN = n;
        long[] fact = new long[maxN + 1];
        long[] invFact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[maxN] = modPow(fact[maxN], MOD - 2);
        for (int i = maxN; i > 0; i--) invFact[i - 1] = invFact[i] * i % MOD;

        long total = comb(n - 1, k - 1, fact, invFact);
        long odd = 0;
        if ((n - k) % 2 == 0) {
            int nOdd = (n + k) / 2;
            odd = comb(nOdd - 1, k - 1, fact, invFact);
        }
        return (int) ((total - odd + MOD) % MOD);
    }

    private long comb(int n, int k, long[] fact, long[] invFact) {
        if (k < 0 || k > n) return 0;
        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

    private long modPow(long a, long e) {
        long res = 1;
        while (e > 0) {
            if ((e & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return res;
    }

    public boolean isCorrect(int n, int k, int output) {
        return output == countValidSequences(n, k);
    }
}