import java.util.*;
class Marker {
    public int scoreDifference(int[] nums) {
        int p1 = 0, p2 = 0;
        boolean firstActive = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1) firstActive = !firstActive;
            if (i % 6 == 5) firstActive = !firstActive;
            if (firstActive) p1 += nums[i];
            else p2 += nums[i];
        }
        return p1 - p2;
    }

    public boolean isCorrect(int[] nums, int output) {
        return scoreDifference(nums) == output;
    }
}