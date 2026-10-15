import java.util.*;
class Marker {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] preMax = new int[n], sufMin = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) preMax[i] = Math.max(preMax[i - 1], nums[i]);
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        for (int i = 0; i < n; i++) {
            if (preMax[i] - sufMin[i] <= k) return i;
        }
        return -1;
    }
    public boolean isCorrect(int[] nums, int k, int output) {
        return output == firstStableIndex(nums, k);
    }
}
