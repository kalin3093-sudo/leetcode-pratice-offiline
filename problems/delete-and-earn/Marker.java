import java.util.*;
class Marker {
  public int deleteAndEarn(int[] nums) {
    // Reduce to 198. House Robber
    int[] bucket = new int[10001];

    for (final int num : nums)
      bucket[num] += num;

    int prev1 = 0;
    int prev2 = 0;

    for (final int num : bucket) {
      final int dp = Math.max(prev1, prev2 + num);
      prev2 = prev1;
      prev1 = dp;
    }

    return prev1;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = deleteAndEarn(nums);
        return expected == output;
    }
}
