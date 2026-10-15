import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    int ans = 0;
    List<Pair<Integer, Integer>> jobs = new ArrayList<>();

    for (int i = 0; i < difficulty.length; ++i)
      jobs.add(new Pair<>(difficulty[i], profit[i]));

    Collections.sort(jobs, Comparator.comparingInt(Pair::getKey));
    Arrays.sort(worker);

    int i = 0;
    int maxProfit = 0;

    for (final int w : worker) {
      for (; i < jobs.size() && w >= jobs.get(i).getKey(); ++i)
        maxProfit = Math.max(maxProfit, jobs.get(i).getValue());
      ans += maxProfit;
    }

    return ans;
  }

    public boolean isCorrect(int[] difficulty, int[] profit, int[] worker, int output) {
        int expected = maxProfitAssignment(difficulty, profit, worker);
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
