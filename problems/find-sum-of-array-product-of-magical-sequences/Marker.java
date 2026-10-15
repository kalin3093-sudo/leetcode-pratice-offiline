import java.util.*;
class Marker {
    static final int MOD = 1_000_000_007;
    public int magicalSum(int m, int k, int[] nums) {
        int len = nums.length;
        int maxBit = len + 6; // enough for carries
        long[][] comb = new long[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) comb[i][j] = (comb[i-1][j-1] + comb[i-1][j]) % MOD;
        }
        long[][] pow = new long[maxBit + 1][m + 1];
        for (int i = 0; i <= maxBit; i++) {
            pow[i][0] = 1;
            int val = (i < len) ? nums[i] : 0;
            for (int c = 1; c <= m; c++) pow[i][c] = pow[i][c-1] * val % MOD;
        }
        long[][][] dp = new long[m + 1][m + 1][k + 1];
        long[][][] ndp = new long[m + 1][m + 1][k + 1];
        dp[0][0][0] = 1;
        for (int pos = 0; pos <= maxBit; pos++) {
            for (int u = 0; u <= m; u++) for (int c = 0; c <= m; c++) for (int b = 0; b <= k; b++) ndp[u][c][b] = 0;
            for (int used = 0; used <= m; used++) {
                for (int carry = 0; carry <= m; carry++) {
                    for (int bits = 0; bits <= k; bits++) {
                        long cur = dp[used][carry][bits];
                        if (cur == 0) continue;
                        int maxCnt = Math.min(m - used, (pos < len) ? m : 0);
                        for (int cnt = 0; cnt <= maxCnt; cnt++) {
                            int total = cnt + carry;
                            int newBit = total & 1;
                            int newCarry = total >> 1;
                            int newBits = bits + newBit;
                            if (newBits > k) continue;
                            int newUsed = used + cnt;
                            long ways = comb[m - used][cnt] * pow[pos][cnt] % MOD;
                            ndp[newUsed][newCarry][newBits] = (ndp[newUsed][newCarry][newBits] + cur * ways) % MOD;
                        }
                    }
                }
            }
            long[][][] tmp = dp; dp = ndp; ndp = tmp;
        }
        return (int)dp[m][0][k];
    }

    public boolean isCorrect(int m, int k, int[] nums, int output) {
        return magicalSum(m, k, nums) == output;
    }
}