import java.util.*;
class Marker {
  public int countWords(String[] words1, String[] words2) {
    Map<String, Integer> count = new HashMap<>();

    for (final String word : words1)
      count.merge(word, 1, Integer::sum);

    for (final String word : words2)
      if (count.containsKey(word) && count.get(word) < 2)
        count.merge(word, -1, Integer::sum);

    return (int) count.values().stream().filter(v -> v == 0).count();
  }

    public boolean isCorrect(String[] words1, String[] words2, int output) {
        int expected = countWords(words1, words2);
        return expected == output;
    }
}
