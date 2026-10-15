import java.util.*;
class Marker {
  public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
    final int n = capacity.length;
    int[] diff = new int[n];

    for (int i = 0; i < n; ++i)
      diff[i] = capacity[i] - rocks[i];

    Arrays.sort(diff);

    for (int i = 0; i < n; ++i) {
      if (diff[i] > additionalRocks)
        return i;
      additionalRocks -= diff[i];
    }

    return n;
  }

    public boolean isCorrect(int[] capacity, int[] rocks, int additionalRocks, int output) {
        int expected = maximumBags(capacity, rocks, additionalRocks);
        return expected == output;
    }
}
