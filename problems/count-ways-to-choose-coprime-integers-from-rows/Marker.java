import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countCoprime(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int maxVal = 0;
        for (int[] row : mat) for (int v : row) maxVal = Math.max(maxVal, v);

        int[][] dp = new int[m][maxVal + 1];
        for (int v : mat[0]) dp[0][v] = (dp[0][v] + 1) % MOD;

        for (int i = 1; i < m; i++) {
            for (int g = 1; g <= maxVal; g++) {
                if (dp[i - 1][g] == 0) continue;
                for (int v : mat[i]) {
                    int ng = gcd(g, v);
                    dp[i][ng] = (dp[i][ng] + dp[i - 1][g]) % MOD;
                }
            }
        }
        return dp[m - 1][1];
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[][] mat, int output) {
        return output == countCoprime(mat);
    }
}