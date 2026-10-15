import java.util.*;
class Marker {
    public int minimumCost(int[] nums, int k) {
        final int MOD = 1000000007;
        long resources = k;
        long totalCost = 0;
        long opsCount = 0;
        
        for (int need : nums) {
            if (resources < need) {
                long deficit = need - resources;
                long x = (deficit + k - 1) / k; // number of operations needed
                // Cost of operations: (opsCount + 1) + (opsCount + 2) + ... + (opsCount + x)
                // = x * opsCount + x * (x + 1) / 2
                totalCost = (totalCost + x * opsCount + x * (x + 1) / 2) % MOD;
                opsCount += x;
                resources += x * k;
            }
            resources -= need;
        }
        
        return (int) (totalCost % MOD);
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return minimumCost(nums, k) == output;
    }
}