import java.util.*;
class Marker {
    private static final int MOD = 1000000007;

    public int dieSimulator(int n, int[] rollMax) {
        // dp[face][run] = sequences ending with `face` and current streak length `run`
        int[][] dp = new int[6][16];
        for (int f = 0; f < 6; f++) dp[f][1] = 1;
        for (int t = 2; t <= n; t++) {
            int[][] nd = new int[6][16];
            for (int f = 0; f < 6; f++) {
                int total = 0;
                for (int r = 1; r <= rollMax[f]; r++) total = (total + dp[f][r]) % MOD;
                for (int g = 0; g < 6; g++) {
                    if (g == f) continue;
                    for (int r = 1; r <= rollMax[g]; r++) nd[f][1] = (nd[f][1] + dp[g][r]) % MOD;
                }
                for (int r = 1; r < rollMax[f]; r++) nd[f][r + 1] = (nd[f][r + 1] + dp[f][r]) % MOD;
            }
            dp = nd;
        }
        int ans = 0;
        for (int f = 0; f < 6; f++)
            for (int r = 1; r <= rollMax[f]; r++) ans = (ans + dp[f][r]) % MOD;
        return ans;
    }

    public boolean isCorrect(int n, int[] rollMax, int output) {
        return dieSimulator(n, rollMax) == output;
    }
}
