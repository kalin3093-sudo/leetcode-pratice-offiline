import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    double ans = Double.MAX_VALUE;
    int qualitySum = 0;
    // (wagePerQuality, quality) sorted by wagePerQuality
    Pair<Double, Integer>[] workers = new Pair[quality.length];
    Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < quality.length; ++i)
      workers[i] = new Pair<>((double) wage[i] / quality[i], quality[i]);

    Arrays.sort(workers, Comparator.comparingDouble(Pair::getKey));

    for (Pair<Double, Integer> worker : workers) {
      final double wagePerQuality = worker.getKey();
      final int q = worker.getValue();
      maxHeap.offer(q);
      qualitySum += q;
      if (maxHeap.size() > k)
        qualitySum -= maxHeap.poll();
      if (maxHeap.size() == k)
        ans = Math.min(ans, qualitySum * wagePerQuality);
    }

    return ans;
  }

    public boolean isCorrect(int[] quality, int[] wage, int k, double output) {
        double expected = mincostToHireWorkers(quality, wage, k);
        return Math.abs(expected - output) < 1e-5;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
