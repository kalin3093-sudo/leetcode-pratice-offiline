import java.util.*;
class Marker {
  public int largestCombination(int[] candidates) {
    final int MAX_BIT = 24;
    int ans = 0;

    for (int i = 0; i < MAX_BIT; ++i) {
      int count = 0;
      for (final int candidate : candidates)
        if ((candidate >> i & 1) == 1)
          ++count;
      ans = Math.max(ans, count);
    }

    return ans;
  }

    public boolean isCorrect(int[] candidates, int output) {
        int expected = largestCombination(candidates);
        return expected == output;
    }
}
