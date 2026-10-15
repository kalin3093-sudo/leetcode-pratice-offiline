import java.util.*;
class Marker {
  public int minPairSum(int[] nums) {
    int ans = 0;

    Arrays.sort(nums);

    for (int i = 0, j = nums.length - 1; i < j;)
      ans = Math.max(ans, nums[i++] + nums[j--]);

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minPairSum(nums);
        return expected == output;
    }
}
