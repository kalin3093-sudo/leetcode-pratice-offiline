import java.util.*;
class Marker {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1_000_000_007L;
        int[] cnt = new int[m + 1], psum = new int[m + 1];
        long[] f = new long[m + 1];
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 0; i < m; i++) {
            int d = s.charAt(i) - '0';
            cnt[i + 1] = cnt[i] + (d != 0 ? 1 : 0);
            psum[i + 1] = psum[i] + d;
            f[i + 1] = f[i];
            pow10[i + 1] = pow10[i] * 10 % MOD;
            if (d != 0) f[i + 1] = (f[i] * 10 + d) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0], r = queries[qi][1];
            int nonz = cnt[r + 1] - cnt[l];
            if (nonz == 0) {
                ans[qi] = 0;
                continue;
            }
            long x = (f[r + 1] - f[l] * pow10[nonz] % MOD + MOD) % MOD;
            int sum = psum[r + 1] - psum[l];
            ans[qi] = (int) (x * sum % MOD);
        }
        return ans;
    }

    public boolean isCorrect(String s, int[][] queries, int[] output) {
        if (output == null) return false;
        return Arrays.equals(sumAndMultiply(s, queries), output);
    }
}
