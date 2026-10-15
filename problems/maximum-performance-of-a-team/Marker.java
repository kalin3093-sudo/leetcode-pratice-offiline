import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  // Similar to 857. Minimum Cost to Hire K Workers
  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    final int MOD = 1_000_000_007;
    long ans = 0;
    long speedSum = 0;
    // (efficiency[i], speed[i]) sorted by efficiency[i] in descending order
    Pair<Integer, Integer>[] A = new Pair[n];
    Queue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < n; ++i)
      A[i] = new Pair<>(efficiency[i], speed[i]);

    Arrays.sort(A, Comparator.comparingInt((Pair<Integer, Integer> p) -> p.getKey()).reversed());

    for (Pair<Integer, Integer> a : A) {
      final int e = a.getKey();
      final int s = a.getValue();
      minHeap.offer(s);
      speedSum += s;
      if (minHeap.size() > k)
        speedSum -= minHeap.poll();
      ans = Math.max(ans, speedSum * e);
    }

    return (int) (ans % MOD);
  }

    public boolean isCorrect(int n, int[] speed, int[] efficiency, int k, int output) {
        int expected = maxPerformance(n, speed, efficiency, k);
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
