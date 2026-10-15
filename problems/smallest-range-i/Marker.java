import java.util.*;
class Marker {
  public int smallestRangeI(int[] nums, int k) {
    final int mx = Arrays.stream(nums).max().getAsInt();
    final int mn = Arrays.stream(nums).min().getAsInt();
    return Math.max(0, mx - mn - 2 * k);
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = smallestRangeI(nums, k);
        return expected == output;
    }
}
