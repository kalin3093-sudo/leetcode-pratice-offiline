import java.util.*;
class Marker {
  public int findMaxK(int[] nums) {
    int ans = -1;
    Set<Integer> seen = new HashSet<>();

    for (final int num : nums)
      if (seen.contains(-num))
        ans = Math.max(ans, Math.abs(num));
      else
        seen.add(num);

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = findMaxK(nums);
        return expected == output;
    }
}
