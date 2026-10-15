import java.util.*;
class Marker {
  public int maximumBeauty(int[] nums, int k) {
    int ans = 0;

    Arrays.sort(nums);

    for (int l = 0, r = 0; r < nums.length; ++r) {
      while (nums[r] - nums[l] > 2 * k)
        ++l;
      ans = Math.max(ans, r - l + 1);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maximumBeauty(nums, k);
        return expected == output;
    }
}
