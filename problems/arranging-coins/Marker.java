import java.util.*;
class Marker {
  public int arrangeCoins(long n) {
    return (int) (-1 + Math.sqrt(8 * n + 1)) / 2;
  }

    public boolean isCorrect(int n, int output) {
        int expected = arrangeCoins(n);
        return expected == output;
    }
}
