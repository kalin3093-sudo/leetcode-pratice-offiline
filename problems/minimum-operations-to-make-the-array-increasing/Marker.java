import java.util.*;
class Marker {
  public int minOperations(int[] nums) {
    int ans = 0;
    int last = 0;

    for (final int num : nums) {
      ans += Math.max(0, last - num + 1);
      last = Math.max(num, last + 1);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minOperations(nums);
        return expected == output;
    }
}
