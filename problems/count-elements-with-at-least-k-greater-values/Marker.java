import java.util.*;

public class Marker {

    public int countElements(int[] nums, int k) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int n = sorted.length;
        int count = 0;
        for (int x : nums) {
            int lo = 0, hi = n;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (sorted[mid] <= x) lo = mid + 1;
                else hi = mid;
            }
            if (n - lo >= k) count++;
        }
        return count;
    }

    public boolean isCorrect(int[] nums, int k, int out) {
        return countElements(nums, k) == out;
    }
}
