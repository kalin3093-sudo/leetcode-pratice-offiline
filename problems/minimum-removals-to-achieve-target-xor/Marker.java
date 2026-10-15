import java.util.*;
class Marker {
    public int minRemovals(int[] nums, int target) {
        int n = nums.length;
        int totalXor = 0;
        for (int x : nums) totalXor ^= x;
        
        if (totalXor == target) return 0;
        
        // Meet in the middle: split array into two halves
        int mid = n / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, n);
        
        Map<Integer, Integer> leftMap = new HashMap<>();
        leftMap.put(0, 0); // xor -> min removals
        
        // Generate all subsets of left half
        for (int mask = 1; mask < (1 << left.length); mask++) {
            int xor = 0;
            int removed = 0;
            for (int i = 0; i < left.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= left[i];
                    removed++;
                }
            }
            leftMap.put(xor, Math.min(leftMap.getOrDefault(xor, Integer.MAX_VALUE), removed));
        }
        
        int best = Integer.MAX_VALUE;
        
        // Check subsets of right half
        for (int mask = 0; mask < (1 << right.length); mask++) {
            int xor = 0;
            int removed = 0;
            for (int i = 0; i < right.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= right[i];
                    removed++;
                }
            }
            int need = target ^ xor;
            if (leftMap.containsKey(need)) {
                best = Math.min(best, leftMap.get(need) + removed);
            }
        }
        
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    public boolean isCorrect(int[] nums, int target, int output) {
        return minRemovals(nums, target) == output;
    }
}