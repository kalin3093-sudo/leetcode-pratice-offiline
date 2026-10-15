import java.util.*;
class Marker {
  public long maxArrayValue(int[] nums) {
    long ans = nums[nums.length - 1];

    for (int i = nums.length - 2; i >= 0; --i)
      if (nums[i] > ans)
        ans = nums[i];
      else
        ans += nums[i];

    return ans;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = maxArrayValue(nums);
        return expected == output;
    }
}
