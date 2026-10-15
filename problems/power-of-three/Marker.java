import java.util.*;
class Marker {
  public boolean isPowerOfThree(int n) {
    return n > 0 && Math.pow(3, 19) % n == 0;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = isPowerOfThree(n);
        return expected == output;
    }
}
