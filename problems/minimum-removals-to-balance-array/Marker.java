import java.util.*;
class Marker {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLen = 0;
        int i = 0;
        for (int j = 0; j < n; j++) {
            while (i <= j && (long) nums[j] > (long) nums[i] * k) {
                i++;
            }
            maxLen = Math.max(maxLen, j - i + 1);
        }
        return n - maxLen;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return minRemoval(nums, k) == output;
    }
}