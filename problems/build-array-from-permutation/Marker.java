import java.util.*;
class Marker {
  public int[] buildArray(int[] nums) {
    final int n = nums.length;

    for (int i = 0; i < n; ++i)
      nums[i] += n * (nums[nums[i]] % n);

    for (int i = 0; i < n; ++i)
      nums[i] /= n;

    return nums;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = buildArray(nums);
        return Arrays.equals(expected, output);
    }
}
