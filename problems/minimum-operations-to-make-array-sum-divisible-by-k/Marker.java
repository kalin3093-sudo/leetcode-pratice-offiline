import java.util.*;
class Marker {
    public int minOperations(int[] nums, int k) {
        int s = 0;
        for (int v : nums) s += v;
        return s % k;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return output == minOperations(nums, k);
    }
}
