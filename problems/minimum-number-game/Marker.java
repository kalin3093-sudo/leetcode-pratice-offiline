import java.util.*;
class Marker {
  public int[] numberGame(int[] nums) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i += 2) {
      final int temp = nums[i];
      nums[i] = nums[i + 1];
      nums[i + 1] = temp;
    }

    return nums;
  }

    public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = numberGame(nums);
        return Arrays.equals(expected, output);
    }
}
