import java.util.*;

class Marker {
    private static final int MOD = 1_000_000_007;

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        int[] prefix = new int[n + 1];
        dp[0] = 1;
        prefix[0] = 1;

        Deque<Integer> minDq = new ArrayDeque<>();
        Deque<Integer> maxDq = new ArrayDeque<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            while (!minDq.isEmpty() && nums[minDq.peekLast()] >= nums[right]) minDq.pollLast();
            minDq.offerLast(right);
            while (!maxDq.isEmpty() && nums[maxDq.peekLast()] <= nums[right]) maxDq.pollLast();
            maxDq.offerLast(right);

            while (nums[maxDq.peekFirst()] - nums[minDq.peekFirst()] > k) {
                if (minDq.peekFirst() == left) minDq.pollFirst();
                if (maxDq.peekFirst() == left) maxDq.pollFirst();
                left++;
            }

            dp[right + 1] = (prefix[right] - (left > 0 ? prefix[left - 1] : 0) + MOD) % MOD;
            prefix[right + 1] = (prefix[right] + dp[right + 1]) % MOD;
        }
        return dp[n];
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return output == countPartitions(nums, k);
    }
}