import java.util.*;
class Marker {
  public int minDeletion(int[] nums) {
    int ans = 0;

    for (int i = 0; i + 1 < nums.length; ++i)
      // i - ans := the index after deletion
      if (nums[i] == nums[i + 1] && (i - ans) % 2 == 0)
        ++ans;

    return ans + (((nums.length - ans) & 1) == 1 ? 1 : 0);
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minDeletion(nums);
        return expected == output;
    }
}
