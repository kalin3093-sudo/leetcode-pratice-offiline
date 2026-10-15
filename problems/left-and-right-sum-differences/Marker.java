import java.util.*;
class Marker {
  public int[] leftRightDifference(int[] nums) {
    final int n = nums.length;
    int[] ans = new int[n];
    int[] leftSum = new int[n];
    int[] rightSum = new int[n];
    int prefix = 0;
    int suffix = 0;

    for (int i = 0; i < n; ++i) {
      if (i > 0)
        prefix += nums[i - 1];
      leftSum[i] = prefix;
    }

    for (int i = n - 1; i >= 0; --i) {
      if (i + 1 < n)
        suffix += nums[i + 1];
      rightSum[i] = suffix;
    }

    for (int i = 0; i < n; ++i)
      ans[i] = Math.abs(leftSum[i] - rightSum[i]);

    return ans;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = leftRightDifference(nums);
        return Arrays.equals(expected, output);
    }
}
