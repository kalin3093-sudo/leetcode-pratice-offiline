import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int minimumLines(int[][] stockPrices) {
    int ans = 0;

    Arrays.sort(stockPrices, Comparator.comparingInt(stockPrice -> stockPrice[0]));

    for (int i = 2; i < stockPrices.length; ++i) {
      Pair<Integer, Integer> a = getSlope(stockPrices[i - 2], stockPrices[i - 1]);
      Pair<Integer, Integer> b = getSlope(stockPrices[i - 1], stockPrices[i]);
      if (a.getKey() != b.getKey() || a.getValue() != b.getValue())
        ++ans;
    }

    return ans + (stockPrices.length > 1 ? 1 : 0);
  }

  private Pair<Integer, Integer> getSlope(int[] p, int[] q) {
    final int dx = p[0] - q[0];
    final int dy = p[1] - q[1];
    if (dx == 0)
      return new Pair<>(0, p[0]);
    if (dy == 0)
      return new Pair<>(p[1], 0);
    final int d = gcd(dx, dy);
    return new Pair<>(dx / d, dy / d);
  }

  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

    public boolean isCorrect(int[][] stockPrices, int output) {
        int expected = minimumLines(stockPrices);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
