import java.util.*;
class Marker {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        
        long[][] dp = new long[m + 1][n + 1];
        for (int c = 0; c <= m; c++) {
            Arrays.fill(dp[c], Long.MIN_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) dp[0][i] = 0; // 0 subarrays = 0 sum
        
        for (int c = 1; c <= m; c++) {
            Deque<Integer> dq = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                dp[c][i] = dp[c][i - 1];
                
                int j = i - l;
                if (j >= 0) {
                    long val = dp[c - 1][j] - pref[j];
                    while (!dq.isEmpty() && (dp[c - 1][dq.peekLast()] - pref[dq.peekLast()]) <= val) {
                        dq.pollLast();
                    }
                    dq.offerLast(j);
                }
                
                while (!dq.isEmpty() && dq.peekFirst() < i - r) {
                    dq.pollFirst();
                }
                
                if (!dq.isEmpty()) {
                    int bestJ = dq.peekFirst();
                    dp[c][i] = Math.max(dp[c][i], pref[i] + dp[c - 1][bestJ] - pref[bestJ]);
                }
            }
        }
        
        long ans = Long.MIN_VALUE;
        for (int c = 1; c <= m; c++) {
            ans = Math.max(ans, dp[c][n]);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, int m, int l, int r, long output) {
        long expected = maximumSum(nums, m, l, r);
        return expected == output;
    }
}