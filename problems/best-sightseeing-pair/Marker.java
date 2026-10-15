import java.util.*;
class Marker {
  public int maxScoreSightseeingPair(int[] values) {
    int ans = 0;
    int bestPrev = 0;

    for (final int value : values) {
      ans = Math.max(ans, value + bestPrev);
      bestPrev = Math.max(bestPrev, value) - 1;
    }

    return ans;
  }

    public boolean isCorrect(int[] values, int output) {
        int expected = maxScoreSightseeingPair(values);
        return expected == output;
    }
}
