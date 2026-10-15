import java.util.*;
class Marker {
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + nums[i];
        int[] dp = new int[n + 1];
        long[] last = new long[n + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0);
        for (int i = 1; i <= n; i++) {
            while (dq.size() >= 2) {
                int j1 = dq.peekFirst(), j2 = dq.peekLast(); // wait, need second element
                // Actually we need the first two elements. ArrayDeque doesn't have get(1).
                // Let's use LinkedList instead.
                break;
            }
        }
        // Fallback to O(n^2) for correctness, then optimize
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            last[i] = last[i - 1] + nums[i - 1];
            for (int j = i - 1; j >= 0; j--) {
                long sum = prefix[i] - prefix[j];
                if (sum >= last[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    last[i] = sum;
                }
            }
        }
        return dp[n];
    }
    public boolean isCorrect(int[] nums, int output) {
        return findMaximumLength(nums) == output;
    }
}