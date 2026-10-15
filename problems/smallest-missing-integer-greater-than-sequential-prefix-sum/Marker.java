import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int missingInteger(int[] nums) {
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());
    int ans = nums[0];

    for (int i = 1; i < nums.length; ++i) {
      if (nums[i] != nums[i - 1] + 1)
        break;
      ans += nums[i];
    }

    while (numsSet.contains(ans))
      ++ans;

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = missingInteger(nums);
        return expected == output;
    }
}
