import java.util.*;
class Marker {
    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            ans += Long.parseLong("" + nums[l] + nums[r]);
            l++;
            r--;
        }
        if (l == r) ans += nums[l];
        return ans;
    }

    public boolean isCorrect(int[] nums, long output) {
        return findTheArrayConcVal(nums) == output;
    }
}