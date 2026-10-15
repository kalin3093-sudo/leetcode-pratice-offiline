import java.util.*;
class Marker {
  public int pivotIndex(int[] nums) {
    final int sum = Arrays.stream(nums).sum();
    int prefix = 0;

    for (int i = 0; i < nums.length; ++i) {
      if (prefix == sum - prefix - nums[i])
        return i;
      prefix += nums[i];
    }

    return -1;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = pivotIndex(nums);
        return expected == output;
    }
}
