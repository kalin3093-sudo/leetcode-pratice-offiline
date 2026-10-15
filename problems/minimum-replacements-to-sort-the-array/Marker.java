import java.util.*;
class Marker {
  public long minimumReplacement(int[] nums) {
    long ans = 0;
    int mx = nums[nums.length - 1];

    for (int i = nums.length - 2; i >= 0; --i) {
      final int ops = (nums[i] - 1) / mx;
      ans += ops;
      mx = nums[i] / (ops + 1);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = minimumReplacement(nums);
        return expected == output;
    }
}
