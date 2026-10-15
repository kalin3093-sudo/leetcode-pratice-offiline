import java.util.*;
class Marker {
    public long nthSmallest(long n, int k) {
        long[][] C = new long[65][65];
        for (int i = 0; i < 65; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++) {
                C[i][j] = Math.min(C[i-1][j-1] + C[i-1][j], (long)2e18);
            }
        }
        long ans = 0;
        for (int bits = 50; bits >= 0 && k > 0; bits--) {
            if (bits < k - 1) continue;
            long cnt = C[bits][k];
            if (n > cnt) {
                n -= cnt;
                ans |= 1L << bits;
                k--;
            }
        }
        return ans;
    }

    public boolean isCorrect(long n, int k, long output) {
        return nthSmallest(n, k) == output;
    }
}