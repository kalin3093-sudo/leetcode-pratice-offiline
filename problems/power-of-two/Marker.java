import java.util.*;
class Marker {
  public boolean isPowerOfTwo(int n) {
    return n >= 0 && Integer.bitCount(n) == 1;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = isPowerOfTwo(n);
        return expected == output;
    }
}
