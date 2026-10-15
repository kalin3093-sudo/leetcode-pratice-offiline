import java.util.*;
class Marker {
    public long splitArray(int[] nums) {
        long sumA = 0, sumB = 0;
        boolean[] prime = new boolean[nums.length + 1];
        Arrays.fill(prime, true);
        if (nums.length >= 1) prime[0] = false;
        if (nums.length >= 2) prime[1] = false;
        for (int i = 2; i * i <= nums.length; i++)
            if (prime[i]) for (int j = i * i; j <= nums.length; j += i) prime[j] = false;
        for (int i = 0; i < nums.length; i++) {
            if (prime[i]) sumA += nums[i]; else sumB += nums[i];
        }
        return Math.abs(sumA - sumB);
    }

    public boolean isCorrect(int[] nums, long output) {
        return splitArray(nums) == output;
    }
}
