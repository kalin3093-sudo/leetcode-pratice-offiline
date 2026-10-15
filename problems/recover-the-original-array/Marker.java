import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] recoverArray(int[] nums) {
    final int n = nums.length;
    Map<Integer, Integer> count = new HashMap<>();

    for (final int num : nums)
      count.merge(num, 1, Integer::sum);

    Arrays.sort(nums);

    for (int i = 1; i < n; ++i) {
      final int x = nums[i] - nums[0]; // 2 * k
      if (x <= 0 || x % 2 == 1)
        continue;
      Map<Integer, Integer> countCopy = new HashMap<>();
      countCopy.putAll(count);
      int[] arr = getArray(nums, x, countCopy);
      if (arr.length == n / 2)
        return arr;
    }

    throw new IllegalArgumentException();
  }

  private int[] getArray(int[] nums, int x, Map<Integer, Integer> count) {
    List<Integer> arr = new ArrayList<>();
    for (final int num : nums) {
      if (count.getOrDefault(num, 0) == 0)
        continue;
      if (count.getOrDefault(num + x, 0) == 0)
        return new int[] {};
      count.merge(num, -1, Integer::sum);
      count.merge(num + x, -1, Integer::sum);
      arr.add(num + x / 2);
    }
    return arr.stream().mapToInt(Integer::intValue).toArray();
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = recoverArray(nums);
        return Arrays.equals(expected, output);
    }
}
