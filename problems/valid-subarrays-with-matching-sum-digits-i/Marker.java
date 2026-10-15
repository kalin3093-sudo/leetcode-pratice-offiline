import java.util.*;

class Marker {
    public int countValidSubarrays(int[] nums, int x) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum % 10 == x) {
                    long t = sum;
                    while (t >= 10) t /= 10;
                    if (t == x) count++;
                }
            }
        }
        return count;
    }

    public boolean isCorrect(int[] nums, int x, int output) {
        return output == countValidSubarrays(nums, x);
    }
}
