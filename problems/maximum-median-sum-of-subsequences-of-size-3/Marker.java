import java.util.*;

class Marker {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long sum = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            sum += nums[right - 1];
            right -= 2;
            left++;
        }
        return sum;
    }

    public boolean isCorrect(int[] nums, long output) {
        return output == maximumMedianSum(nums);
    }
}