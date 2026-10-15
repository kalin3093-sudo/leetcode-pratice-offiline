import java.util.*;
class Marker {
  public boolean isPossibleToSplit(int[] nums) {
    final int MAX = 100;
    int[] count = new int[MAX + 1];

    for (final int num : nums)
      ++count[num];

    return Arrays.stream(count).allMatch(freq -> freq <= 2);
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = isPossibleToSplit(nums);
        return expected == output;
    }
}
