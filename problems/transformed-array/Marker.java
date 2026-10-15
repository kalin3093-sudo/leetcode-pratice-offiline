import java.util.*;
class Marker {
  public int[] constructTransformedArray(int[] nums) {
    final int n = nums.length;
    int[] ans = new int[n];

    for (int i = 0; i < n; ++i)
      ans[i] = nums[(i + nums[i] % n + n) % n];

    return ans;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = constructTransformedArray(nums);
        return Arrays.equals(expected, output);
    }
}
