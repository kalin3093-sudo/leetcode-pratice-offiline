import java.util.*;
class Marker {
    public int minSwaps(int[] nums, int[] forbidden) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);
        for (int x : forbidden) freq.merge(x, 1, Integer::sum);
        
        for (int v : freq.values()) {
            if (v >= n + 1) return -1;
        }
        
        Map<Integer, Integer> badPairs = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == forbidden[i]) {
                badPairs.merge(nums[i], 1, Integer::sum);
            }
        }
        
        int badPairsSum = 0;
        int maxBadPairs = 0;
        for (int v : badPairs.values()) {
            badPairsSum += v;
            maxBadPairs = Math.max(maxBadPairs, v);
        }
        
        return Math.max((badPairsSum + 1) / 2, maxBadPairs);
    }

    public boolean isCorrect(int[] nums, int[] forbidden, int output) {
        return minSwaps(nums, forbidden) == output;
    }
}