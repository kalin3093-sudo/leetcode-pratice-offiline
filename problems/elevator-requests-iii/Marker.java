import java.util.*;
class Marker {
    public long elevatorRequests(int n, int start, int[][] requests) {
        int m = requests.length;
        int[] arrival = new int[m];
        int[] floor = new int[m];
        for (int i = 0; i < m; i++) {
            arrival[i] = requests[i][0];
            floor[i] = requests[i][1];
        }
        
        int full = (1 << m) - 1;
        long[][] dp = new long[1 << m][m];
        for (long[] row : dp) Arrays.fill(row, Long.MAX_VALUE / 2);
        
        for (int i = 0; i < m; i++) {
            long t = Math.abs(start - floor[i]);
            dp[1 << i][i] = Math.max(t, arrival[i]);
        }
        
        for (int mask = 1; mask <= full; mask++) {
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) == 0) continue;
                long cur = dp[mask][i];
                if (cur == Long.MAX_VALUE / 2) continue;
                for (int j = 0; j < m; j++) {
                    if ((mask & (1 << j)) != 0) continue;
                    long t = cur + Math.abs(floor[i] - floor[j]);
                    t = Math.max(t, arrival[j]);
                    int nmask = mask | (1 << j);
                    dp[nmask][j] = Math.min(dp[nmask][j], t);
                }
            }
        }
        
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans, dp[full][i]);
        }
        return ans;
    }

    public boolean isCorrect(int n, int start, int[][] requests, long output) {
        return elevatorRequests(n, start, requests) == output;
    }
}