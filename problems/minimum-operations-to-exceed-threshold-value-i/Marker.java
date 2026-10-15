import java.util.*;
class Marker {
  public int minOperations(int[] nums, int k) {
    return (int) Arrays.stream(nums).filter(num -> num < k).count();
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = minOperations(nums, k);
        return expected == output;
    }
}
