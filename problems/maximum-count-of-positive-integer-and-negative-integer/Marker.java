import java.util.*;
class Marker {
  public int maximumCount(int[] nums) {
    return (int) Math.max(Arrays.stream(nums).filter(num -> num > 0).count(),
                          Arrays.stream(nums).filter(num -> num < 0).count());
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maximumCount(nums);
        return expected == output;
    }
}
