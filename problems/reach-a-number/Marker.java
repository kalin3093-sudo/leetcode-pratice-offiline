import java.util.*;
class Marker {
  public int reachNumber(int target) {
    final int newTarget = Math.abs(target);
    int ans = 0;
    int pos = 0;

    while (pos < newTarget)
      pos += ++ans;
    while ((pos - newTarget) % 2 == 1)
      pos += ++ans;

    return ans;
  }

    public boolean isCorrect(int target, int output) {
        int expected = reachNumber(target);
        return expected == output;
    }
}
