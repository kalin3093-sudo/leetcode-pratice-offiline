import java.util.*;
class Marker {
  public int smallestEvenMultiple(int n) {
    return n * (n % 2 + 1);
  }

    public boolean isCorrect(int n, int output) {
        int expected = smallestEvenMultiple(n);
        return expected == output;
    }
}
