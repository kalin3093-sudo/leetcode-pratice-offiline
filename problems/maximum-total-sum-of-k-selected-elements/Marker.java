import java.util.*;
class Marker {
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length;
        
        // Select k largest elements
        int multCount = Math.min(k, mul - 1);
        // The multCount largest elements get multiplied by mul, mul-1, ..., mul - multCount + 1
        // The remaining k - multCount elements are just added
        
        // We should pair largest multipliers with largest elements
        int idx = n - 1;
        for (int i = 0; i < multCount; i++) {
            sum += (long) nums[idx] * (mul - i);
            idx--;
        }
        for (int i = 0; i < k - multCount; i++) {
            sum += nums[idx];
            idx--;
        }
        return sum;
    }

    public boolean isCorrect(int[] nums, int k, int mul, long output) {
        long expected = maxSum(nums, k, mul);
        return expected == output;
    }
}