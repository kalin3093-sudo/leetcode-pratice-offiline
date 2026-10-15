import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] kWeakestRows(int[][] mat, int k) {
    int[] ans = new int[k];
    Pair<Integer, Integer>[] rowSums = new Pair[mat.length];

    for (int i = 0; i < mat.length; ++i)
      rowSums[i] = new Pair<>(Arrays.stream(mat[i]).sum(), i);

    Arrays.sort(rowSums, Comparator.comparingInt(Pair<Integer, Integer>::getKey)
                             .thenComparingInt(Pair<Integer, Integer>::getValue));

    for (int i = 0; i < k; ++i)
      ans[i] = rowSums[i].getValue();

    return ans;
  }

    public boolean isCorrect(int[][] mat, int k, int[] output) {
        int[] expected = kWeakestRows(mat, k);
        return Arrays.equals(expected, output);
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
