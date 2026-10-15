import java.util.*;
class Marker {
    public long maxEarnings(int[][] meetings) {
        int n = meetings.length;
        int[][] m = new int[n][3];
        for (int i = 0; i < n; i++) {
            m[i][0] = meetings[i][0];
            m[i][1] = meetings[i][1];
            m[i][2] = meetings[i][2];
        }
        Arrays.sort(m, (a, b) -> Integer.compare(a[1], b[1]));
        
        long[] dp = new long[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) ends[i] = m[i][1];
        
        long[] maxVal = new long[n];
        
        for (int i = 0; i < n; i++) {
            int s = m[i][0];
            int e = m[i][1];
            int rev = m[i][2];
            
            // Find last meeting with end <= s
            int j = Arrays.binarySearch(ends, 0, i, s);
            if (j < 0) j = ~j;
            j--; // last index with end <= s
            
            long best = rev; // take only this meeting
            if (j >= 0) {
                best = rev + s + maxVal[j];
            }
            
            dp[i] = best;
            maxVal[i] = dp[i] - e;
            if (i > 0) maxVal[i] = Math.max(maxVal[i], maxVal[i - 1]);
        }
        
        long ans = 0;
        for (long v : dp) ans = Math.max(ans, v);
        return ans;
    }

    public boolean isCorrect(int[][] meetings, long output) {
        return maxEarnings(meetings) == output;
    }
}