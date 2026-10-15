import java.util.*;
class Marker {
  public int minimumOperations(int[] nums) {
    return (int) Arrays.stream(nums).filter(num -> num % 3 != 0).count();
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = minimumOperations(nums);
        return expected == output;
    }
}
