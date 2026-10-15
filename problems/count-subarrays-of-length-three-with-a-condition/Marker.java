import java.util.*;
class Marker {
  public int countSubarrays(int[] nums) {
    int ans = 0;

    for (int i = 1; i + 1 < nums.length; ++i)
      if (nums[i] == (nums[i - 1] + nums[i + 1]) * 2)
        ++ans;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = countSubarrays(nums);
        return expected == output;
    }
}
