import java.util.*;
class Marker {
  public int waysToSplitArray(int[] nums) {
    int ans = 0;
    long prefix = 0;
    long suffix = Arrays.stream(nums).asLongStream().sum();

    for (int i = 0; i < nums.length - 1; ++i) {
      prefix += nums[i];
      suffix -= nums[i];
      if (prefix >= suffix)
        ++ans;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = waysToSplitArray(nums);
        return expected == output;
    }
}
