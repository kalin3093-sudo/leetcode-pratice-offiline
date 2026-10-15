import java.util.*;
class Marker {
    private static final int MOD = 1000000007;

    public int countVisiblePeople(int n, int pos, int k) {
        int a = pos, b = n - pos - 1;
        int max = Math.min(a, k);
        long fact[] = new long[n + 1], invFact[] = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[n] = modInverse(fact[n]);
        for (int i = n; i > 0; i--) invFact[i - 1] = invFact[i] * i % MOD;
        long sum = 0;
        for (int i = Math.max(0, k - b); i <= max; i++) {
            int j = k - i;
            if (j < 0 || j > b) continue;
            sum = (sum + comb(fact, invFact, a, i) * comb(fact, invFact, b, j)) % MOD;
        }
        return (int) (2 * sum % MOD);
    }

    private long comb(long[] fact, long[] invFact, int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    private long modInverse(long a) {
        long b = MOD, u = 1, v = 0;
        while (b > 0) {
            long t = a / b;
            a -= t * b;
            long tmp = a; a = b; b = tmp;
            t = u - t * v;
            u = v; v = tmp;
        }
        return (u % MOD + MOD) % MOD;
    }

    public boolean isCorrect(int n, int pos, int k, int output) {
        return countVisiblePeople(n, pos, k) == output;
    }
}
