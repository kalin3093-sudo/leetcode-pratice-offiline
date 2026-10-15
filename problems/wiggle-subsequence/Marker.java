import java.util.*;
class Marker {
  public int wiggleMaxLength(int[] nums) {
    int increasing = 1;
    int decreasing = 1;

    for (int i = 1; i < nums.length; ++i)
      if (nums[i] > nums[i - 1])
        increasing = decreasing + 1;
      else if (nums[i] < nums[i - 1])
        decreasing = increasing + 1;

    return Math.max(increasing, decreasing);
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = wiggleMaxLength(nums);
        return expected == output;
    }
}
