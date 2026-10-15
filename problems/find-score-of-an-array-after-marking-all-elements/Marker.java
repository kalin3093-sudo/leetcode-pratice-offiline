import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public long findScore(int[] nums) {
    long ans = 0;
    TreeSet<Pair<Integer, Integer>> numAndIndexes =
        new TreeSet<>(Comparator.comparingInt(Pair<Integer, Integer>::getKey)
                          .thenComparingInt(Pair<Integer, Integer>::getValue));
    boolean[] seen = new boolean[nums.length];

    for (int i = 0; i < nums.length; ++i)
      numAndIndexes.add(new Pair<>(nums[i], i));

    for (Pair<Integer, Integer> pair : numAndIndexes) {
      final int num = pair.getKey();
      final int i = pair.getValue();
      if (seen[i])
        continue;
      if (i > 0)
        seen[i - 1] = true;
      if (i + 1 < nums.length)
        seen[i + 1] = true;
      seen[i] = true;
      ans += num;
    }

    return ans;
  }

    public boolean isCorrect(int[] nums, long output) {
        long expected = findScore(nums);
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
