import java.util.*;
class Marker {
  public int maxOperations(int[] nums, int k) {
    int ans = 0;
    HashMap<Integer, Integer> count = new HashMap<>();

    for (final int num : nums)
      count.merge(num, 1, Integer::sum);

    for (final int num : count.keySet()) {
      final int complement = k - num;
      if (count.containsKey(complement))
        ans += Math.min(count.get(num), count.get(complement));
    }

    return ans / 2;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = maxOperations(nums, k);
        return expected == output;
    }
}
