import java.util.*;
class Marker {
    public int absDifference(int[] nums, int k) {
        int[] a = nums.clone();
        Arrays.sort(a);
        int lo = 0, hi = 0;
        for (int i = 0; i < k; i++) {
            lo += a[i];
            hi += a[a.length - 1 - i];
        }
        return hi - lo;
    }

    public boolean isCorrect(int[] nums, int k, int output) {
        return absDifference(nums, k) == output;
    }
}
