import java.util.*;

class Marker {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int p = 1;
        while (p * 2 <= n) p *= 2;
        return p * 2;
    }

    public boolean isCorrect(int[] nums, int output) {
        return output == uniqueXorTriplets(nums);
    }
}
