import java.util.*;
class Marker {
  public long maxAlternatingSum(int[] nums) {
    long even = 0; // the maximum alternating sum ending in an even index
    long odd = 0;  // the maximum alternating sum ending in an odd index

    for (final int num : nums) {
      even = Math.max(even, odd + num);
      odd = even - num;
    }

    return even;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = maxAlternatingSum(nums);
        return expected == output;
    }
}
