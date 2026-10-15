import java.util.*;
class Marker {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        
        // Check if any positive subarray sum exists
        long maxSingle = Long.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            int j = i - l;
            if (j >= 0) {
                while (!dq.isEmpty() && pref[dq.peekLast()] >= pref[j]) dq.pollLast();
                dq.offerLast(j);
            }
            while (!dq.isEmpty() && dq.peekFirst() < i - r) dq.pollFirst();
            if (!dq.isEmpty()) {
                maxSingle = Math.max(maxSingle, pref[i] - pref[dq.peekFirst()]);
            }
        }
        
        // If all subarray sums are <= 0, must pick the best single subarray
        if (maxSingle <= 0) {
            return maxSingle;
        }
        
        // Otherwise, use WQS for at most m subarrays (will naturally pick >= 1)
        long left = -100000, right = 100000;
        long bestAns = Long.MIN_VALUE;
        
        while (left <= right) {
            long mid = (left + right) >> 1;
            var res = check(pref, n, l, r, mid);
            if (res.count <= m) {
                bestAns = Math.max(bestAns, res.sum + mid * m);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (left <= 100000) {
            var res = check(pref, n, l, r, left);
            bestAns = Math.max(bestAns, res.sum + left * m);
        }
        
        return bestAns;
    }
    
    private static class Result {
        long sum;
        int count;
        Result(long sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    
    private Result check(long[] pref, int n, int l, int r, long penalty) {
        long[] dp = new long[n + 1];
        int[] cnt = new int[n + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            cnt[i] = cnt[i - 1];
            
            int j = i - l;
            if (j >= 0) {
                long val = dp[j] - pref[j] - penalty;
                int c = cnt[j];
                while (!dq.isEmpty()) {
                    int back = dq.peekLast();
                    long backVal = dp[back] - pref[back] - penalty;
                    int backCnt = cnt[back];
                    if (val > backVal || (val == backVal && c < backCnt)) {
                        dq.pollLast();
                    } else {
                        break;
                    }
                }
                dq.offerLast(j);
            }
            
            while (!dq.isEmpty() && dq.peekFirst() < i - r) {
                dq.pollFirst();
            }
            
            if (!dq.isEmpty()) {
                int bestJ = dq.peekFirst();
                long cand = pref[i] + dp[bestJ] - pref[bestJ] - penalty;
                if (cand > dp[i]) {
                    dp[i] = cand;
                    cnt[i] = cnt[bestJ] + 1;
                } else if (cand == dp[i]) {
                    cnt[i] = Math.min(cnt[i], cnt[bestJ] + 1);
                }
            }
        }
        return new Result(dp[n], cnt[n]);
    }

    public boolean isCorrect(int[] nums, int m, int l, int r, long output) {
        long expected = maximumSum(nums, m, l, r);
        return expected == output;
    }
}