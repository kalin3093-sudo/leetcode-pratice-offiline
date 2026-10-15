import java.util.*;

class Marker {
    private static final long MOD = 1000000007L;

    public int minNonZeroProduct(int p) {
        if (p == 1) return 1;
        long maxVal = (1L << p) - 1;
        long pairs = (1L << (p - 1)) - 1;
        long term = (maxVal - 1) % MOD;
        long pow = modPow(term, pairs);
        long result = (pow * (maxVal % MOD)) % MOD;
        return (int) result;
    }

    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    public boolean isCorrect(int p, int output) {
        return minNonZeroProduct(p) == output;
    }
}