import java.util.*;
class Marker {
  public int minFlips(String target) {
    int ans = 0;
    int state = 0;

    for (final char c : target.toCharArray())
      if (c - '0' != state) {
        state = c - '0';
        ++ans;
      }

    return ans;
  }

    public boolean isCorrect(String target, int output) {
        int expected = minFlips(target);
        return expected == output;
    }
}
