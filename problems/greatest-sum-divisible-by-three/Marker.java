import java.util.*;
class Marker {
  public int maxSumDivThree(int[] nums) {
    int[] dp = new int[3]; // dp[i] := the maximum sum so far s.t. sum % 3 == i

    for (final int num : nums)
      for (final int sum : dp.clone())
        dp[(sum + num) % 3] = Math.max(dp[(sum + num) % 3], sum + num);

    return dp[0];
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxSumDivThree(nums);
        return expected == output;
    }
}
