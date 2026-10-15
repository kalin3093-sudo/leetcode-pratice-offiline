import java.util.*;
class Marker {
  public int distinctAverages(int[] nums) {
    final int n = nums.length;
    Set<Integer> sums = new HashSet<>();

    Arrays.sort(nums);

    for (int i = 0; i < n / 2; ++i)
      sums.add(nums[i] + nums[n - 1 - i]);

    return sums.size();
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = distinctAverages(nums);
        return expected == output;
    }
}
