import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback,
                                   String[] report, int[] student_id, int k) {
    List<Integer> ans = new ArrayList<>();
    Pair<Integer, Integer>[] scoreAndIds = new Pair[report.length];
    Set<String> pos = Arrays.stream(positive_feedback).collect(Collectors.toSet());
    Set<String> neg = Arrays.stream(negative_feedback).collect(Collectors.toSet());

    for (int i = 0; i < report.length; ++i) {
      int score = 0;
      for (final String word : report[i].split(" ")) {
        if (pos.contains(word))
          score += 3;
        if (neg.contains(word))
          score -= 1;
      }
      scoreAndIds[i] = new Pair<>(score, student_id[i]);
    }

    Arrays.sort(scoreAndIds,
                Comparator.comparing(Pair<Integer, Integer>::getKey, Comparator.reverseOrder())
                    .thenComparing(Pair<Integer, Integer>::getValue));

    for (int i = 0; i < k; ++i)
      ans.add(scoreAndIds[i].getValue());
    return ans;
  }

    public boolean isCorrect(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k, List<Integer> output) {
        List<Integer> expected = topStudents(positive_feedback, negative_feedback, report, student_id, k);
        return expected.equals(output);
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
