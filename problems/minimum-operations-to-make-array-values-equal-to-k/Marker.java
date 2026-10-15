import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int minOperations(int[] nums, int k) {
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    final int mn = Arrays.stream(nums).min().getAsInt();
    if (mn < k)
      return -1;
    if (mn > k)
      return numsSet.size();
    return numsSet.size() - 1;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = minOperations(nums, k);
        return expected == output;
    }
}
