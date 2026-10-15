import java.util.*;
class Marker {
  public int maxAbsoluteSum(int[] nums) {
    int ans = Integer.MIN_VALUE;
    int maxSum = 0;
    int minSum = 0;

    for (final int num : nums) {
      maxSum = Math.max(num, maxSum + num);
      minSum = Math.min(num, minSum + num);
      ans = Math.max(ans, Math.max(maxSum, -minSum));
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxAbsoluteSum(nums);
        return expected == output;
    }
}
