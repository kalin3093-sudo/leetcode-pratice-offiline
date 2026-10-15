import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countVowelPermutation(int n) {
        if (n == 1) return 5;
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int len = 2; len <= n; len++) {
            long na = (e + i + u) % MOD;
            long ne = (a + i) % MOD;
            long ni = (e + o) % MOD;
            long no = i % MOD;
            long nu = (i + o) % MOD;
            a = na; e = ne; i = ni; o = no; u = nu;
        }
        return (int) ((a + e + i + o + u) % MOD);
    }

    public boolean isCorrect(int n, int output) {
        return output == countVowelPermutation(n);
    }
}