import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int idealArrays(int n, int maxValue) {
        List<Integer>[] divisors = new List[maxValue + 1];
        for (int i = 1; i <= maxValue; i++) {
            divisors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= maxValue; i++) {
            for (int j = 2 * i; j <= maxValue; j += i) {
                divisors[j].add(i);
            }
        }

        int[][] dp = new int[n + 1][maxValue + 1];
        for (int v = 1; v <= maxValue; v++) dp[1][v] = 1;

        for (int len = 2; len <= n; len++) {
            for (int v = 1; v <= maxValue; v++) {
                long sum = 0;
                for (int d : divisors[v]) {
                    sum += dp[len - 1][d];
                }
                dp[len][v] = (int) (sum % MOD);
            }
        }

        long[][] comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        long ans = 0;
        for (int v = 1; v <= maxValue; v++) {
            for (int len = 1; len <= n; len++) {
                if (dp[len][v] == 0) continue;
                ans = (ans + dp[len][v] * comb[n - 1][len - 1]) % MOD;
            }
        }
        return (int) ans;
    }

    public boolean isCorrect(int n, int maxValue, int output) {
        return output == idealArrays(n, maxValue);
    }
}