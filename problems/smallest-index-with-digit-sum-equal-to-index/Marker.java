import java.util.*;

class Marker {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            int s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            if (s == i)
                return i;
        }
        return -1;
    }

    public boolean isCorrect(int[] nums, int output) {
        return smallestIndex(nums) == output;
    }
}
