import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int findMaxValueOfEquation(int[][] points, int k) {
    int ans = Integer.MIN_VALUE;
    // (y - x, x)
    Queue<Pair<Integer, Integer>> maxHeap =
        new PriorityQueue<>(Comparator.comparing(Pair::getKey, Comparator.reverseOrder()));

    for (int[] p : points) {
      final int x = p[0];
      final int y = p[1];
      while (!maxHeap.isEmpty() && x - maxHeap.peek().getValue() > k)
        maxHeap.poll();
      if (!maxHeap.isEmpty())
        ans = Math.max(ans, x + y + maxHeap.peek().getKey());
      maxHeap.offer(new Pair<>(y - x, x));
    }

    return ans;
  }

    public boolean isCorrect(int[][] points, int k, int output) {
        int expected = findMaxValueOfEquation(points, k);
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
