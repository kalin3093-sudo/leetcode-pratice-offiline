import java.util.*;
class Marker {
  public long numberOfWeeks(int[] milestones) {
    // The best strategy is to pick "max, nonMax, max, nonMax, ...".
    final int mx = Arrays.stream(milestones).max().getAsInt();
    final long sum = Arrays.stream(milestones).asLongStream().sum();
    final long nonMax = sum - mx;
    return Math.min(sum, 2 * nonMax + 1);
  }

    public boolean isCorrect(int[] milestones, long output) {
        long expected = numberOfWeeks(milestones);
        return expected == output;
    }
}
