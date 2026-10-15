import java.util.*;
class Marker {
  public int arrayNesting(int[] nums) {
    int ans = 0;

    for (final int num : nums) {
      if (num == -1)
        continue;
      int index = num;
      int count = 0;
      while (nums[index] != -1) {
        final int cache = index;
        index = nums[index];
        nums[cache] = -1;
        ++count;
      }
      ans = Math.max(ans, count);
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = arrayNesting(nums);
        return expected == output;
    }
}
