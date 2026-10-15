import java.util.*;
class Marker {
  public int maxIncreasingGroups(List<Integer> usageLimits) {
    int ans = 1; // the next target length
    long availableLimits = 0;

    Collections.sort(usageLimits);

    for (final int usageLimit : usageLimits) {
      availableLimits += usageLimit;
      // Can create groups 1, 2, ..., ans.
      if (availableLimits >= ans * (long) (ans + 1) / 2)
        ++ans;
    }

    return ans - 1;
  }

    public boolean isCorrect(List<Integer> usageLimits, int output) {
        int expected = maxIncreasingGroups(usageLimits);
        return expected == output;
    }
}
