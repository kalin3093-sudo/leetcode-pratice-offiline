import java.util.*;
class Marker {
  public int maxDistinctElements(int[] nums, int k) {
    int ans = 0;
    int occupied = Integer.MIN_VALUE;

    Arrays.sort(nums);

    for (final int num : nums)
      if (occupied < num + k) {
        occupied = Math.max(occupied + 1, num - k);
        ++ans;
      }

    return ans;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maxDistinctElements(nums, k);
        return expected == output;
    }
}
