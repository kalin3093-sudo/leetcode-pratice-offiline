import java.util.*;
class Marker {
  public int minChanges(int n, int k) {
    // n needs to be a superset of k.
    return (n & k) == k ? Integer.bitCount(n ^ k) : -1;
  }

    public boolean isCorrect(int n, int k, int output) {
        int expected = minChanges(n, k);
        return expected == output;
    }
}
