import java.util.*;
class Marker {
  public int minimumDifference(int[] nums, int k) {
    Arrays.sort(nums);

    int ans = nums[k - 1] - nums[0];

    for (int i = k; i < nums.length; ++i)
      ans = Math.min(ans, nums[i] - nums[i - k + 1]);

    return ans;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = minimumDifference(nums, k);
        return expected == output;
    }
}
