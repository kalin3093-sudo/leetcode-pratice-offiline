import java.util.*;
class Marker {
  public int minOperations(List<Integer> nums, int k) {
    Set<Integer> seen = new HashSet<>();

    for (int i = nums.size() - 1; i >= 0; --i) {
      if (nums.get(i) > k)
        continue;
      seen.add(nums.get(i));
      if (seen.size() == k)
        return nums.size() - i;
    }

    throw new IllegalArgumentException();
  }

    public boolean isCorrect(List<Integer> nums, int k, int output) {
        int expected = minOperations(nums, k);
        return expected == output;
    }
}
