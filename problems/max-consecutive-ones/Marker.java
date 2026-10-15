import java.util.*;
class Marker {
  public int findMaxConsecutiveOnes(int[] nums) {
    int ans = 0;
    int sum = 0;

    for (final int num : nums)
      if (num == 1)
        ans = Math.max(ans, ++sum);
      else
        sum = 0;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = findMaxConsecutiveOnes(nums);
        return expected == output;
    }
}
