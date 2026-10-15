import java.util.*;
class Marker {
    public boolean kLengthApart(int[] nums, int k) {
        int last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (last >= 0 && i - last - 1 < k) return false;
                last = i;
            }
        }
        return true;
    }

    public boolean isCorrect(int[] nums, int k, boolean output) {
        return kLengthApart(nums, k) == output;
    }
}
