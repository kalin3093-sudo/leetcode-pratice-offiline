import java.util.*;

class Marker {
    private static final int MOD = 1000000007;

    public int makeStringSorted(String s) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < c; j++) {
                if (freq[j] == 0) continue;
                freq[j]--;
                long ways = fact[n - i - 1];
                for (int k = 0; k < 26; k++) {
                    ways = ways * invFact[freq[k]] % MOD;
                }
                ans = (ans + ways) % MOD;
                freq[j]++;
            }
            freq[c]--;
        }
        return (int) ans;
    }

    private long modPow(long a, long e) {
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return r;
    }

    public boolean isCorrect(String s, int output) {
        return makeStringSorted(s) == output;
    }
}