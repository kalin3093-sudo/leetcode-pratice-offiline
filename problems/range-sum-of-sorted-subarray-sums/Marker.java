import java.util.*;
class Marker {
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Long> sums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int j = i; j < n; j++) { cur += nums[j]; sums.add(cur); }
        }
        Collections.sort(sums);
        long total = 0;
        for (int i = left - 1; i <= right - 1; i++) total += sums.get(i);
        return (int) (total % 1000000007L);
    }

    public boolean isCorrect(int[] nums, int n, int left, int right, int output) {
        return rangeSum(nums, n, left, right) == output;
    }
}
