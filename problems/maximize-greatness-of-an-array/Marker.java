import java.util.*;
class Marker {
  public int maximizeGreatness(int[] nums) {
    int ans = 0;

    Arrays.sort(nums);

    for (final int num : nums)
      if (num > nums[ans])
        ++ans;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maximizeGreatness(nums);
        return expected == output;
    }
}
