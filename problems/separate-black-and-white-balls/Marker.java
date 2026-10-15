import java.util.*;
class Marker {
  public long minimumSteps(String s) {
    long ans = 0;
    int ones = 0;

    for (final char c : s.toCharArray())
      if (c == '1')
        ++ones;
      else // Move 1s to the front of the current '0'.
        ans += ones;

    return ans;
  }

    public boolean isCorrect(String s, long output) {
        long expected = minimumSteps(s);
        return expected == output;
    }
}
