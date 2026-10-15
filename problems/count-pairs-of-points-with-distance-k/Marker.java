import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int countPairs(List<List<Integer>> coordinates, int k) {
    int ans = 0;

    for (int x = 0; x <= k; ++x) {
      final int y = k - x;
      Map<Pair<Integer, Integer>, Integer> count = new HashMap<>();
      for (List<Integer> point : coordinates) {
        final int xi = point.get(0);
        final int yi = point.get(1);
        ans += count.getOrDefault(new Pair<>(xi ^ x, yi ^ y), 0);
        count.merge(new Pair<>(xi, yi), 1, Integer::sum);
      }
    }

    return ans;
  }

    public boolean isCorrect(List<List<Integer>> coordinates, int k, int output) {
        int expected = countPairs(coordinates, k);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
