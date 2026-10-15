import java.util.*;
class Marker {
  public int destroyTargets(int[] nums, int space) {
    int ans = Integer.MAX_VALUE;
    int maxCount = 0;
    Map<Integer, Integer> count = new HashMap<>();

    for (final int num : nums)
      maxCount = Math.max(maxCount, count.merge(num % space, 1, Integer::sum));

    for (final int num : nums)
      if (count.get(num % space) == maxCount)
        ans = Math.min(ans, num);

    return ans;
  }

    public boolean isCorrect(int[] nums, int space, int output) {
        int expected = destroyTargets(nums, space);
        return expected == output;
    }
}
