import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maxSum(int[] nums) {
    final int mx = Arrays.stream(nums).max().getAsInt();
    if (mx <= 0)
      return mx;
    return Arrays.stream(nums)
        .boxed()
        .collect(Collectors.toSet())
        .stream()
        .filter(num -> num > 0)
        .mapToInt(Integer::intValue)
        .sum();
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxSum(nums);
        return expected == output;
    }
}
