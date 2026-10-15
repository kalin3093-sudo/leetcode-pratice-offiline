import java.util.*;
class Marker {
  public int minMoves(int[] nums) {
    final int sum = Arrays.stream(nums).sum();
    final int mn = Arrays.stream(nums).min().getAsInt();
    return sum - mn * nums.length;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minMoves(nums);
        return expected == output;
    }
}
