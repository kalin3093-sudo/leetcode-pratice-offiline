import java.util.*;
class Marker {
    public int smallestBalancedIndex(int[] nums) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + nums[i];
        long[] suffixProd = new long[n + 1];
        suffixProd[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            long p = suffixProd[i + 1] * nums[i];
            if (p > 1_000_000_000_000L) p = 1_000_000_000_001L; // cap to avoid overflow
            suffixProd[i] = p;
        }
        for (int i = 0; i < n; i++) {
            if (prefixSum[i] == suffixProd[i + 1]) return i;
        }
        return -1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return smallestBalancedIndex(nums) == output;
    }
}