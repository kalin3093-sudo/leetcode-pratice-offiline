import java.util.*;
class Marker {
  public int minStartValue(int[] nums) {
    int sum = 0;
    int minSum = 0;

    for (final int num : nums)
      minSum = Math.min(minSum, sum += num);

    return 1 - minSum;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minStartValue(nums);
        return expected == output;
    }
}
