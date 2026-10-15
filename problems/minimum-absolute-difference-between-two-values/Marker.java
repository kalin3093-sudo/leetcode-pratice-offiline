import java.util.*;
class Marker {
    public int minAbsoluteDifference(int[] nums) {
        int last1 = -1, last2 = -1, ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                last1 = i;
                if (last2 >= 0) ans = Math.min(ans, i - last2);
            } else if (nums[i] == 2) {
                last2 = i;
                if (last1 >= 0) ans = Math.min(ans, i - last1);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public boolean isCorrect(int[] nums, int output) {
        return minAbsoluteDifference(nums) == output;
    }
}
