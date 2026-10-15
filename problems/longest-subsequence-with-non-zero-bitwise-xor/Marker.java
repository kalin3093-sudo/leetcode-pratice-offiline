import java.util.*;
class Marker {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;
        
        if (xor != 0) return nums.length;
        
        boolean hasNonZero = false;
        for (int x : nums) {
            if (x != 0) {
                hasNonZero = true;
                break;
            }
        }
        if (!hasNonZero) return 0;
        
        return nums.length - 1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return longestSubsequence(nums) == output;
    }
}