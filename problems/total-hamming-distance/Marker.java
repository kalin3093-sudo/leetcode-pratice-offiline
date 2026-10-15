import java.util.*;
class Marker {
  public int totalHammingDistance(int[] nums) {
    final int MAX_BIT = 30;
    int ans = 0;

    for (int i = 0; i < MAX_BIT; ++i) {
      final int mask = 1 << i;
      final int ones = (int) Arrays.stream(nums).filter(num -> (num & mask) > 0).count();
      final int zeros = nums.length - ones;
      ans += ones * zeros;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = totalHammingDistance(nums);
        return expected == output;
    }
}
