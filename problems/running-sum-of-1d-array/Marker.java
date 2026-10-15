import java.util.*;
class Marker {
  public int[] runningSum(int[] nums) {
    int[] ans = new int[nums.length];
    int sum = 0;
    for (int i = 0; i < nums.length; ++i)
      ans[i] = sum += nums[i];
    return ans;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = runningSum(nums);
        return Arrays.equals(expected, output);
    }
}
