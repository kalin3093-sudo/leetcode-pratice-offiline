import java.util.*;
class Marker {
  public int maxScore(int[] nums) {
    long prefix = 0;

    nums = Arrays.stream(nums)
               .boxed()
               .sorted(Collections.reverseOrder())
               .mapToInt(Integer::intValue)
               .toArray();

    for (int i = 0; i < nums.length; ++i) {
      prefix += nums[i];
      if (prefix <= 0)
        return i;
    }

    return nums.length;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxScore(nums);
        return expected == output;
    }
}
