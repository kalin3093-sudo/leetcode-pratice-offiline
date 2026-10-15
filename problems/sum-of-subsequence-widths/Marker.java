import java.util.*;
class Marker {
  public int sumSubseqWidths(int[] nums) {
    final int MOD = 1_000_000_007;
    final int n = nums.length;
    long ans = 0;
    long exp = 1;

    Arrays.sort(nums);

    for (int i = 0; i < n; ++i, exp = exp * 2 % MOD) {
      ans += (nums[i] - nums[n - 1 - i]) * exp;
      ans %= MOD;
    }

    return (int) ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = sumSubseqWidths(nums);
        return expected == output;
    }
}
