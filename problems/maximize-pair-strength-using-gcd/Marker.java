import java.util.*;
class Marker {
    public long maxPairStrength(int[] nums) {
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long a = nums[i];
                long b = nums[j];
                long g = gcd(a, b);
                long strength = a * b / (g * g);
                ans = Math.max(ans, strength);
            }
        }
        return ans;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public boolean isCorrect(int[] nums, long output) {
        return maxPairStrength(nums) == output;
    }
}