import java.util.*;
class Marker {
  public int minElement(int[] nums) {
    int ans = Integer.MAX_VALUE;
    for (final int num : nums)
      ans = Math.min(ans, getDigitSum(num));
    return ans;
  }

  private int getDigitSum(int num) {
    int digitSum = 0;
    while (num > 0) {
      digitSum += num % 10;
      num /= 10;
    }
    return digitSum;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minElement(nums);
        return expected == output;
    }
}
