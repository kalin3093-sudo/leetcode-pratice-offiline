import java.util.*;
class Marker {
  public boolean divideArray(int[] nums) {
    int[] count = new int[501];

    for (final int num : nums)
      ++count[num];

    return Arrays.stream(count).allMatch(c -> c % 2 == 0);
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = divideArray(nums);
        return expected == output;
    }
}
