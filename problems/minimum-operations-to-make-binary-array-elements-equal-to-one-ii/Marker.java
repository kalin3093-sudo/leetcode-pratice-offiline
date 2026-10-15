import java.util.*;
class Marker {
  public int minOperations(int[] nums) {
    int ans = 0;
    int target = 1;

    for (final int num : nums)
      if (num != target) {
        target ^= 1;
        ++ans;
      }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minOperations(nums);
        return expected == output;
    }
}
