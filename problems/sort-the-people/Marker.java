import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public String[] sortPeople(String[] names, int[] heights) {
    List<Pair<Integer, String>> heightAndNames = new ArrayList<>();

    for (int i = 0; i < names.length; ++i)
      heightAndNames.add(new Pair<>(heights[i], names[i]));

    Collections.sort(heightAndNames, (a, b) -> b.getKey().compareTo(a.getKey()));

    for (int i = 0; i < heightAndNames.size(); ++i)
      names[i] = heightAndNames.get(i).getValue();

    return names;
  }

    public boolean isCorrect(String[] names, int[] heights, String[] output) {
        String[] expected = sortPeople(names, heights);
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
