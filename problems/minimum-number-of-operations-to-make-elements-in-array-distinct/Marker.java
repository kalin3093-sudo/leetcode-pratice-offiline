import java.util.*;
class Marker {
  public int minimumOperations(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int i = nums.length - 1; i >= 0; --i)
      if (!seen.add(nums[i]))
        return (i + 1 + 2) / 3; // ceil((i + 1) / 3)
    return 0;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minimumOperations(nums);
        return expected == output;
    }
}
