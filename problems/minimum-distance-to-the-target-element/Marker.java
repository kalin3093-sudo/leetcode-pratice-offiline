import java.util.*;
class Marker {
  public int getMinDistance(int[] nums, int target, int start) {
    int ans = Integer.MAX_VALUE;

    for (int i = 0; i < nums.length; ++i)
      if (nums[i] == target)
        ans = Math.min(ans, Math.abs(i - start));

    return ans;
  }

    public boolean isCorrect(int[] nums, int target, int start, int output) {
        int expected = getMinDistance(nums, target, start);
        return expected == output;
    }
}
