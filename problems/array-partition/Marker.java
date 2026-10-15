import java.util.*;
class Marker {
  public int arrayPairSum(int[] nums) {
    int ans = 0;

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i += 2)
      ans += nums[i];

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = arrayPairSum(nums);
        return expected == output;
    }
}
