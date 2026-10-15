import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int minimumOperations(int[] nums) {
    Set<Integer> seen = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    return seen.size() - (seen.contains(0) ? 1 : 0);
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minimumOperations(nums);
        return expected == output;
    }
}
