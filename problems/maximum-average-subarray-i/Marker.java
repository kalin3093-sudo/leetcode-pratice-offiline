import java.util.*;
class Marker {
  public double findMaxAverage(int[] nums, int k) {
    double sum = 0;
    for (int i = 0; i < k; ++i)
      sum += nums[i];
    double ans = sum;

    for (int i = k; i < nums.length; ++i) {
      sum += nums[i] - nums[i - k];
      ans = Math.max(ans, sum);
    }

    return ans / k;
  }

    public boolean isCorrect(int[] nums, int k, double output) {
        double expected = findMaxAverage(nums, k);
        return Math.abs(expected - output) < 1e-5;
    }
}
