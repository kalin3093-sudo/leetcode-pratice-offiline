import java.util.*;
class Marker {
  public int triangularSum(int[] nums) {
    for (int sz = nums.length; sz > 0; --sz)
      for (int i = 0; i + 1 < sz; ++i)
        nums[i] = (nums[i] + nums[i + 1]) % 10;
    return nums[0];
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = triangularSum(nums);
        return expected == output;
    }
}
