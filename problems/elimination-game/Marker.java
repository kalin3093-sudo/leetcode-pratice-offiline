import java.util.*;
class Marker {
  public int lastRemaining(int n) {
    return n == 1 ? 1 : 2 * (1 + n / 2 - lastRemaining(n / 2));
  }

    public boolean isCorrect(int n, int output) {
        int expected = lastRemaining(n);
        return expected == output;
    }
}
