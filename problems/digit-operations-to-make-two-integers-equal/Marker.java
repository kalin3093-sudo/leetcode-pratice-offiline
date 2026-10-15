import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int minOperations(int n, int m) {
    final int MAX = 10000;
    boolean[] isPrime = sieveEratosthenes(MAX);
    if (isPrime[n] || isPrime[m])
      return -1;
    return dijkstra(n, m, isPrime);
  }

  private int dijkstra(int src, int dst, boolean[] isPrime) {
    Set<Integer> seen = new HashSet<>(Arrays.asList(src));
    // (cost, num)
    Queue<Pair<Integer, Integer>> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
    minHeap.offer(new Pair<>(src, src));

    while (!minHeap.isEmpty()) {
      final int cost = minHeap.peek().getKey();
      final int curr = minHeap.poll().getValue();
      if (curr == dst)
        return cost;
      final String s = Integer.toString(curr);
      for (int i = 0; i < s.length(); ++i) {
        char[] chars = s.toCharArray();
        if (chars[i] < '9') {
          ++chars[i];
          final int next = Integer.parseInt(new String(chars));
          if (!isPrime[next] && !seen.contains(next)) {
            minHeap.offer(new Pair<>(cost + next, next));
            seen.add(next);
          }
          --chars[i];
        }
        if (chars[i] > '0' && !(i == 0 && chars[i] == '1')) {
          --chars[i];
          final int next = Integer.parseInt(new String(chars));
          if (!isPrime[next] && !seen.contains(next)) {
            minHeap.offer(new Pair<>(cost + next, next));
            seen.add(next);
          }
        }
      }
    }

    return -1;
  }

  private boolean[] sieveEratosthenes(int n) {
    boolean[] isPrime = new boolean[n];
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i * i < n; ++i)
      if (isPrime[i])
        for (int j = i * i; j < n; j += i)
          isPrime[j] = false;
    return isPrime;
  }

    public boolean isCorrect(int n, int m, int output) {
        int expected = minOperations(n, m);
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
