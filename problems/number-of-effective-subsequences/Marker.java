import java.util.*;

class Marker {
    public int countEffective(int[] nums) {
        final int MOD = 1_000_000_007;
        int T = 0;
        for (int v : nums) T |= v;
        int p = Integer.bitCount(T);
        if (p == 0) return 0;
        int[] bitPos = new int[p];
        int idx = 0;
        for (int b = 0; b < 31; b++) if (((T >> b) & 1) == 1) bitPos[idx++] = b;
        int size = 1 << p;
        long[] w = new long[size];
        for (int v : nums) {
            int c = 0;
            for (int i = 0; i < p; i++) if (((v >> bitPos[i]) & 1) == 1) c |= 1 << i;
            w[c]++;
        }
        long[] zsum = w.clone();
        for (int i = 0; i < p; i++) {
            for (int m = 0; m < size; m++) {
                if ((m & (1 << i)) != 0) zsum[m] += zsum[m ^ (1 << i)];
            }
        }
        long ans = 0;
        int full = size - 1;
        for (int M = 1; M < size; M++) {
            long cnt = zsum[full ^ M];
            long term = 1;
            for (int i = 0; i < cnt; i++) {
                term <<= 1;
                if (term >= MOD) term -= MOD;
            }
            if ((Integer.bitCount(M) & 1) == 1) ans += term;
            else ans -= term;
            ans %= MOD;
            if (ans < 0) ans += MOD;
        }
        return (int) ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == countEffective(nums);
    }
}
