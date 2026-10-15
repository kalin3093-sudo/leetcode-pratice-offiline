import java.util.*;
class Marker {
  public long flowerGame(int n, int m) {
    // Alice wins if x + y is odd, occurring when:
    //   1. x is even and y is odd, or
    //   2. y is even and x is odd.
    final int xEven = n / 2;
    final int yEven = m / 2;
    final int xOdd = (n + 1) / 2;
    final int yOdd = (m + 1) / 2;
    return (long) xEven * yOdd + (long) yEven * xOdd;
  }

    public boolean isCorrect(int n, int m, long output) {
        long expected = flowerGame(n, m);
        return expected == output;
    }
}
