import java.util.*;

public class Marker {
    static final int MOD = 1000000007;

    public int countAnagrams(String s) {
        String[] words = s.split(" ");
        int max = 0;
        for (String w : words) max = Math.max(max, w.length());
        long[] fact = new long[max + 1];
        long[] inv = new long[max + 1];
        fact[0] = 1;
        for (int i = 1; i <= max; i++) fact[i] = fact[i - 1] * i % MOD;
        inv[max] = pow(fact[max], MOD - 2);
        for (int i = max; i >= 1; i--) inv[i - 1] = inv[i] * i % MOD;
        long ans = 1;
        for (String w : words) {
            int[] cnt = new int[26];
            for (char ch : w.toCharArray()) cnt[ch - 'a']++;
            long ways = fact[w.length()];
            for (int c : cnt) ways = ways * inv[c] % MOD;
            ans = ans * ways % MOD;
        }
        return (int) ans;
    }

    long pow(long a, long e) {
        long r = 1;
        while (e > 0) {
            if ((e & 1) == 1) r = r * a % MOD;
            a = a * a % MOD;
            e >>= 1;
        }
        return r;
    }

    public boolean isCorrect(String s, int out) {
        return countAnagrams(s) == out;
    }
}
