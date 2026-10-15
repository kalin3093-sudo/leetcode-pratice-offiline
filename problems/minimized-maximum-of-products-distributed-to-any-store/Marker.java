import java.util.*;
class Marker {
  public int minimizedMaximum(int n, int[] quantities) {
    int l = 1;
    int r = Arrays.stream(quantities).max().getAsInt();

    while (l < r) {
      final int m = (l + r) / 2;
      if (numStores(quantities, m) <= n)
        r = m;
      else
        l = m + 1;
    }

    return l;
  }

  private int numStores(int[] quantities, int m) {
    // ceil(q / m)
    return Arrays.stream(quantities).reduce(0, (subtotal, q) -> subtotal + (q - 1) / m + 1);
  }

    public boolean isCorrect(int n, int[] quantities, int output) {
        int expected = minimizedMaximum(n, quantities);
        return expected == output;
    }
}
