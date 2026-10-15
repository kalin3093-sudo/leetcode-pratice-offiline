import java.util.*;
class Marker {
  public boolean isMonotonic(int[] nums) {
    boolean increasing = true;
    boolean decreasing = true;

    for (int i = 1; i < nums.length; ++i) {
      increasing &= nums[i] >= nums[i - 1];
      decreasing &= nums[i] <= nums[i - 1];
    }

    return increasing || decreasing;
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = isMonotonic(nums);
        return expected == output;
    }
}
