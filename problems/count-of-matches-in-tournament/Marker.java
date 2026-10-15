import java.util.*;
class Marker {
  public int numberOfMatches(int n) {
    return n - 1;
  }

    public boolean isCorrect(int n, int output) {
        int expected = numberOfMatches(n);
        return expected == output;
    }
}
