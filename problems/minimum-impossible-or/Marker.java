import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int minImpossibleOR(int[] nums) {
    int ans = 1;
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    while (numsSet.contains(ans))
      ans <<= 1;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minImpossibleOR(nums);
        return expected == output;
    }
}
