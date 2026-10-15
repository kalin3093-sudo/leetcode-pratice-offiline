import java.util.*;
class Marker {
    public int minFlips(String s) {
        // dp[a][b]: a = longest matched prefix of "011", b = of "110", as subsequences (3 = forbidden)
        int[] p1 = {0, 1, 1}, p2 = {1, 1, 0};
        int INF = 1 << 29;
        int[][] dp = new int[3][3];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;
        for (int i = 0; i < s.length(); i++) {
            int[][] next = new int[3][3];
            for (int[] row : next) Arrays.fill(row, INF);
            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    if (dp[a][b] == INF) continue;
                    for (int c = 0; c <= 1; c++) {
                        int cost = dp[a][b] + ((s.charAt(i) - '0') == c ? 0 : 1);
                        int na = (a < 3 && c == p1[a]) ? a + 1 : a;
                        int nb = (b < 3 && c == p2[b]) ? b + 1 : b;
                        if (na < 3 && nb < 3) next[na][nb] = Math.min(next[na][nb], cost);
                    }
                }
            }
            dp = next;
        }
        int ans = INF;
        for (int[] row : dp) for (int v : row) ans = Math.min(ans, v);
        return ans;
    }

    public boolean isCorrect(String s, int output) {
        int expected = minFlips(s);
        return expected == output;
    }
}
