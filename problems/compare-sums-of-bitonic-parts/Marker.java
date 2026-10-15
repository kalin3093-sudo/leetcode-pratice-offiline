import java.util.*;
class Marker {
    public int compareBitonicSums(int[] nums) {
        int peak = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[peak]) peak = i;
        }
        int asc = 0, desc = 0;
        for (int i = 0; i <= peak; i++) asc += nums[i];
        for (int i = peak; i < nums.length; i++) desc += nums[i];
        if (asc == desc) return -1;
        return asc > desc ? 0 : 1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return compareBitonicSums(nums) == output;
    }
}
