import java.util.*;

class Marker {
  public int maxDigitRange(int[] nums) {
    int best = 0;
    for (int x : nums) best = Math.max(best, digitRange(x));
    int sum = 0;
    for (int x : nums) {
      if (digitRange(x) == best) sum += x;
    }
    return sum;
  }

  public boolean isCorrect(int[] nums, int output) {
    return maxDigitRange(nums) == output;
  }

  private int digitRange(int x) {
    int lo = 9, hi = 0;
    while (x > 0) {
      int d = x % 10;
      lo = Math.min(lo, d);
      hi = Math.max(hi, d);
      x /= 10;
    }
    return hi - lo;
  }
}
