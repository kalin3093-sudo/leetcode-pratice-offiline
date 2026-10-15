import java.util.*;
class Marker {
  public int[] getMaximumXor(int[] nums, int maximumBit) {
    final int n = nums.length;
    final int mx = (1 << maximumBit) - 1;
    int[] ans = new int[n];
    int xors = 0;

    for (int i = 0; i < n; ++i) {
      xors ^= nums[i];
      ans[n - 1 - i] = xors ^ mx;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int maximumBit, int[] output) {
        int[] expected = getMaximumXor(nums, maximumBit);
        return Arrays.equals(expected, output);
    }
}
