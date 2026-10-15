public class Marker {
    static final int MOD = 1000000007;

    public int interleaveCharacters(String word1, String word2, String target) {
        int m1 = word1.length(), m2 = word2.length(), mT = target.length();
        long[][] dp = new long[m1 + 1][m2 + 1];
        dp[0][0] = 1;
        for (int t = 0; t < mT; t++) {
            char c = target.charAt(t);
            long[][] nd = new long[m1 + 1][m2 + 1];
            for (int j = 0; j <= m2; j++) {
                long sum = 0;
                for (int i = 0; i <= m1; i++) {
                    sum += dp[i][j];
                    if (sum >= MOD) sum -= MOD;
                    if (i < m1 && word1.charAt(i) == c) {
                        nd[i + 1][j] += sum;
                        if (nd[i + 1][j] >= MOD) nd[i + 1][j] -= MOD;
                    }
                }
            }
            for (int i = 0; i <= m1; i++) {
                long sum = 0;
                for (int j = 0; j <= m2; j++) {
                    sum += dp[i][j];
                    if (sum >= MOD) sum -= MOD;
                    if (j < m2 && word2.charAt(j) == c) {
                        nd[i][j + 1] += sum;
                        if (nd[i][j + 1] >= MOD) nd[i][j + 1] -= MOD;
                    }
                }
            }
            dp = nd;
        }
        long ans = 0;
        for (int i = 1; i <= m1; i++)
            for (int j = 1; j <= m2; j++) {
                ans += dp[i][j];
                if (ans >= MOD) ans -= MOD;
            }
        return (int) ans;
    }

    public boolean isCorrect(String word1, String word2, String target, int out) {
        return interleaveCharacters(word1, word2, target) == out;
    }
}
