import java.util.*;
class Marker {
  public boolean isPossibleDivide(int[] nums, int k) {
    TreeMap<Integer, Integer> count = new TreeMap<>();

    for (final int num : nums)
      count.merge(num, 1, Integer::sum);

    for (final int start : count.keySet()) {
      final int value = count.getOrDefault(start, 0);
      if (value > 0)
        for (int i = start; i < start + k; ++i)
          if (count.merge(i, -value, Integer::sum) < 0)
            return false;
    }

    return true;
  }

    public boolean isCorrect(int[] nums, int k, boolean output) {
        boolean expected = isPossibleDivide(nums, k);
        return expected == output;
    }
}
