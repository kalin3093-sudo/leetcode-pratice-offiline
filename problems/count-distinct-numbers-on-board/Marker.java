import java.util.*;
class Marker {
  public int distinctIntegers(int n) {
    return Math.max(n - 1, 1);
  }

    public boolean isCorrect(int n, int output) {
        int expected = distinctIntegers(n);
        return expected == output;
    }
}
