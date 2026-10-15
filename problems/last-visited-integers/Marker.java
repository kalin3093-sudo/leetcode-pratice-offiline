import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<Integer> lastVisitedIntegers(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    List<Integer> seen = new ArrayList<>();
    int k = 0;

    for (final int num : nums)
      if (num == -1) {
        ++k;
        ans.add(k > seen.size() ? -1 : seen.get(seen.size() - k));
      } else {
        k = 0;
        seen.add(num);
      }

    return ans;
  }

    public boolean isCorrect(int[] nums, List<Integer> output) {
        List<Integer> expected = lastVisitedIntegers(nums);
        return expected.equals(output);
    }
}
