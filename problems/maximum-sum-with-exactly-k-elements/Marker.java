import java.util.*;
class Marker {
  public int maximizeSum(int[] nums, int k) {
    // If x = max(nums), ans = x + (x + 1) + .. + (x + k - 1).
    final int x = Arrays.stream(nums).max().getAsInt();
    return x * k + k * (k - 1) / 2;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maximizeSum(nums, k);
        return expected == output;
    }
}
