import java.util.*;

class Marker {
    public long popcountDepth(long n, int k) {
        if (k == 0) return n >= 1 ? 1 : 0;
        String bits = Long.toBinaryString(n);
        int L = bits.length();
        long[][] dp = new long[L + 1][2];
        dp[0][1] = 1;
        for (int i = 0; i < L; i++) {
            long[][] nd = new long[L + 1][2];
            int b = bits.charAt(i) - '0';
            for (int ones = 0; ones <= i; ones++) {
                for (int tight = 0; tight < 2; tight++) {
                    long ways = dp[ones][tight];
                    if (ways == 0) continue;
                    int hi = tight == 1 ? b : 1;
                    for (int d = 0; d <= hi; d++) {
                        int nt = (tight == 1 && d == hi) ? 1 : 0;
                        nd[ones + d][nt] += ways;
                    }
                }
            }
            dp = nd;
        }
        long ans = 0;
        for (int ones = 1; ones <= L; ones++) {
            long count = dp[ones][0] + dp[ones][1];
            if (count == 0) continue;
            if (depth(ones) == k - 1) {
                ans += count;
                if (ones == 1) ans--;
            }
        }
        return ans;
    }

    private int depth(int j) {
        int d = 0;
        while (j != 1) {
            j = Integer.bitCount(j);
            d++;
        }
        return d;
    }

    public boolean isCorrect(long n, int k, long output) {
        return output == popcountDepth(n, k);
    }
}
