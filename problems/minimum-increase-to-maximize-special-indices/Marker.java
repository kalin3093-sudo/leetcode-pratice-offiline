import java.util.*;
class Marker {
    public long minIncrease(int[] nums) {
        int n = nums.length;
        int m = n - 2; // candidate peak indices 1..n-2
        // cost to make original index i a strict peak
        long[] cost = new long[n];
        for (int i = 1; i <= n - 2; i++) {
            cost[i] = Math.max(0L, (long)Math.max(nums[i - 1], nums[i + 1]) + 1L - nums[i]);
        }
        if (m % 2 == 1) {
            // unique max independent set: odd positions among candidates
            long sum = 0;
            for (int i = 1; i <= n - 2; i += 2) sum += cost[i];
            return sum;
        }
        // m even: max independent sets = odds-prefix up to a split, evens-suffix after
        long[] preOdd = new long[n + 1]; // preOdd[i]: sum of cost at odd candidate positions <= i
        long[] sufEven = new long[n + 2]; // sufEven[i]: sum of cost at even candidate positions >= i
        for (int i = 1; i <= n - 2; i++) {
            preOdd[i] = preOdd[i - 1] + (i % 2 == 1 ? cost[i] : 0);
        }
        for (int i = n - 2; i >= 1; i--) {
            sufEven[i] = sufEven[i + 1] + (i % 2 == 0 ? cost[i] : 0);
        }
        long ans = Long.MAX_VALUE;
        for (int j = 0; j <= m / 2; j++) {
            // odds at 1,3,...,2j-1 plus evens at 2j+2,2j+4,...,m
            long cur = preOdd[2 * j] + sufEven[2 * j + 2];
            ans = Math.min(ans, cur);
        }
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        long expected = minIncrease(nums);
        return expected == output;
    }
}
