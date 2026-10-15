import java.util.*;
class Marker {
  public int minIncrementForUnique(int[] nums) {
    int ans = 0;
    int minAvailable = 0;

    Arrays.sort(nums);

    for (final int num : nums) {
      ans += Math.max(minAvailable - num, 0);
      minAvailable = Math.max(minAvailable, num) + 1;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minIncrementForUnique(nums);
        return expected == output;
    }
}
