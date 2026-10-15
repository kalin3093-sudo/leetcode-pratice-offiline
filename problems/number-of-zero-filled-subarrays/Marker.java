import java.util.*;
class Marker {
  public long zeroFilledSubarray(int[] nums) {
    long ans = 0;
    int indexBeforeZero = -1;

    for (int i = 0; i < nums.length; ++i)
      if (nums[i] == 0)
        ans += i - indexBeforeZero;
      else
        indexBeforeZero = i;

    return ans;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = zeroFilledSubarray(nums);
        return expected == output;
    }
}
