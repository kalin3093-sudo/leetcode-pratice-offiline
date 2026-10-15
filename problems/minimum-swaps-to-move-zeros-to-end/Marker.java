import java.util.*;
class Marker {
    public int minimumSwaps(int[] nums) {
        int n = nums.length;
        int totalZeros = 0;
        for (int x : nums) if (x == 0) totalZeros++;
        
        if (totalZeros == 0) return 0;
        
        // Final zero zone is the last totalZeros positions
        int zerosInZone = 0;
        for (int i = n - totalZeros; i < n; i++) {
            if (nums[i] == 0) zerosInZone++;
        }
        
        // Zeros outside the zone need to be swapped in
        return totalZeros - zerosInZone;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minimumSwaps(nums) == output;
    }
}