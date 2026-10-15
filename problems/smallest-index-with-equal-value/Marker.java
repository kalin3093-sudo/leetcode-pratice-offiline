import java.util.*;
class Marker {
  public int smallestEqual(int[] nums) {
    for (int i = 0; i < nums.length; ++i)
      if (nums[i] == i % 10)
        return i;
    return -1;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = smallestEqual(nums);
        return expected == output;
    }
}
