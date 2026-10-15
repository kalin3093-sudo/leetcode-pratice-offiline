import java.util.*;
class Marker {
  public int minElements(int[] nums, int limit, int goal) {
    final long sum = Arrays.stream(nums).asLongStream().sum();
    final double diff = Math.abs(goal - sum);
    return (int) Math.ceil(diff / limit);
  }

    public boolean isCorrect(int[] nums, int limit, int goal, int output) {
        int expected = minElements(nums, limit, goal);
        return expected == output;
    }
}
