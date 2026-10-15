import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maxPoints(int[][] points) {
    int ans = 0;

    for (int i = 0; i < points.length; ++i) {
      Map<Pair<Integer, Integer>, Integer> slopeCount = new HashMap<>();
      int[] p1 = points[i];
      int samePoints = 1;
      int maxPoints = 0; // the maximum number of points with the same slope
      for (int j = i + 1; j < points.length; ++j) {
        int[] p2 = points[j];
        if (p1[0] == p2[0] && p1[1] == p2[1])
          ++samePoints;
        else {
          Pair<Integer, Integer> slope = getSlope(p1, p2);
          slopeCount.merge(slope, 1, Integer::sum);
          maxPoints = Math.max(maxPoints, slopeCount.get(slope));
        }
      }
      ans = Math.max(ans, samePoints + maxPoints);
    }

    return ans;
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

    public boolean isCorrect(int[][] points, int output) {
        int expected = maxPoints(points);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    // required: slopeCount is a HashMap<Pair, Integer> keyed by value equality
    @Override public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }
    @Override public int hashCode() { return Objects.hash(key, value); }
}
