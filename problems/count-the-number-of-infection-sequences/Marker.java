import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int numberOfSequence(int n, int[] sick) {
        int m = sick.length;
        List<Integer> segments = new ArrayList<>();
        int totalLen = 0;
        int pow2 = 0;

        int prev = -1;
        for (int i = 0; i < m; i++) {
            int gap = sick[i] - prev - 1;
            if (gap > 0) {
                segments.add(gap);
                totalLen += gap;
                if (prev >= 0) pow2 += gap - 1;
            }
            prev = sick[i];
        }
        int gap = n - prev - 1;
        if (gap > 0) {
            segments.add(gap);
            totalLen += gap;
        }

        long[] fact = new long[totalLen + 1];
        fact[0] = 1;
        for (int i = 1; i <= totalLen; i++) fact[i] = fact[i - 1] * i % MOD;

        long[] invFact = new long[totalLen + 1];
        invFact[totalLen] = modPow(fact[totalLen], MOD - 2);
        for (int i = totalLen; i > 0; i--) invFact[i - 1] = invFact[i] * i % MOD;

        long ans = fact[totalLen];
        for (int len : segments) {
            ans = ans * invFact[len] % MOD;
        }
        ans = ans * modPow(2, pow2) % MOD;
        return (int) ans;
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

    public boolean isCorrect(int n, int[] sick, int output) {
        return output == numberOfSequence(n, sick);
    }
}