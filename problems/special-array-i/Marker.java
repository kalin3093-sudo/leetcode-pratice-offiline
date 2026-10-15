import java.util.*;
class Marker {
  public boolean isArraySpecial(int[] nums) {
    for (int i = 1; i < nums.length; ++i)
      if (nums[i] % 2 == nums[i - 1] % 2)
        return false;
    return true;
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = isArraySpecial(nums);
        return expected == output;
    }
}
