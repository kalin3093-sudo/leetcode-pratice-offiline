import java.util.*;
class Marker {
  public int findLengthOfLCIS(int[] nums) {
    int ans = 0;

    for (int l = 0, r = 0; r < nums.length; ++r) {
      if (r > 0 && nums[r] <= nums[r - 1])
        l = r;
      ans = Math.max(ans, r - l + 1);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = findLengthOfLCIS(nums);
        return expected == output;
    }
}
