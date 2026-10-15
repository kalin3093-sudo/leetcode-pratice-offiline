import java.util.*;
class Marker {
  public int sumOfSquares(int[] nums) {
    final int n = nums.length;
    int ans = 0;

    for (int i = 0; i < n; ++i)
      if (n % (i + 1) == 0)
        ans += nums[i] * nums[i];

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = sumOfSquares(nums);
        return expected == output;
    }
}
