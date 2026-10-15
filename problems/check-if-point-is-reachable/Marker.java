import java.util.*;
class Marker {
  public boolean isReachable(int targetX, int targetY) {
    return Integer.bitCount(gcd(targetX, targetY)) == 1;
  }

  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

    public boolean isCorrect(int targetX, int targetY, boolean output) {
        boolean expected = isReachable(targetX, targetY);
        return expected == output;
    }
}
