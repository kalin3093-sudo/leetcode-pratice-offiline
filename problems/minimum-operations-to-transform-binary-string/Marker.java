import java.util.*;
class Marker {
    public int minOperations(String s1, String s2) {
        int n = s1.length();
        final int INF = 1 << 29;
        int[] dp = new int[2];
        dp[0] = 0; dp[1] = INF;
        for (int i = 0; i < n; i++) {
            int[] ndp = { INF, INF };
            for (int f = 0; f < 2; f++) {
                if (dp[f] == INF) continue;
                char b = (f == 1) ? '0' : s1.charAt(i);
                // option A: no pair at i
                int cA;
                if (s2.charAt(i) == b) cA = dp[f];
                else if (b == '0' && s2.charAt(i) == '1') cA = dp[f] + 1;
                else cA = INF;
                if (cA < INF) ndp[0] = Math.min(ndp[0], cA);
                // option B: pair (i, i+1)
                if (i + 1 < n) {
                    int cB = dp[f];
                    cB += (b == '1') ? 0 : 1;
                    cB += (s1.charAt(i + 1) == '1') ? 0 : 1;
                    cB += 1;
                    cB += (s2.charAt(i) == '0') ? 0 : 1;
                    ndp[1] = Math.min(ndp[1], cB);
                }
            }
            dp = ndp;
        }
        int res = Math.min(dp[0], dp[1]);
        return res == INF ? -1 : res;
    }

    public boolean isCorrect(String s1, String s2, int output) {
        return output == minOperations(s1, s2);
    }
}
