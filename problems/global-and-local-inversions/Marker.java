import java.util.*;
class Marker {
  public boolean isIdealPermutation(int[] nums) {
    int mx = -1; // the number that is most likely > nums[i + 2]

    for (int i = 0; i + 2 < nums.length; ++i) {
      mx = Math.max(mx, nums[i]);
      if (mx > nums[i + 2])
        return false;
    }

    return true;
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = isIdealPermutation(nums);
        return expected == output;
    }
}
