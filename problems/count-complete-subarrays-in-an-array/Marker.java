import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int countCompleteSubarrays(int[] nums) {
    final int MAX = 2000;
    final int totalDistinct = Arrays.stream(nums).boxed().collect(Collectors.toSet()).size();
    int ans = 0;
    int distinct = 0;
    int[] count = new int[MAX + 1];

    int l = 0;
    for (final int num : nums) {
      if (++count[num] == 1)
        ++distinct;
      while (distinct == totalDistinct)
        if (--count[nums[l++]] == 0)
          --distinct;
      // Assume nums[r] = num,
      // nums[0..r], nums[1..r], ..., nums[l - 1..r] have k different values.
      ans += l;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = countCompleteSubarrays(nums);
        return expected == output;
    }
}
