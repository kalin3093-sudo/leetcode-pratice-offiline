import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public String oddString(String[] words) {
    List<Pair<String, Integer>> wordAndHashCodes = getWordAndHashCodes(words);
    Map<Integer, Integer> hashCodeCount = new HashMap<>();

    for (Pair<String, Integer> wordAndHashCode : wordAndHashCodes)
      hashCodeCount.merge(wordAndHashCode.getValue(), 1, Integer::sum);

    for (Pair<String, Integer> wordAndHashCode : wordAndHashCodes)
      if (hashCodeCount.get(wordAndHashCode.getValue()) == 1)
        return wordAndHashCode.getKey();

    throw new IllegalArgumentException();
  }

  // Returns the pairs of the word and its corresponding difference string.
  // e.g. [("adc", "3#-1#"), ("wzy", "3#-1#"), ("abc", "1#1#")]
  private List<Pair<String, Integer>> getWordAndHashCodes(String[] words) {
    List<Pair<String, Integer>> wordAndHashCodes = new ArrayList<>();
    for (final String word : words)
      wordAndHashCodes.add(new Pair<>(word, getDiffStr(word).hashCode()));
    return wordAndHashCodes;
  }

  // Returns the difference string of `s`.
  // e.g. getDiffStr("adc") -> "3#-1#"
  private String getDiffStr(final String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < s.length(); ++i)
      sb.append(s.charAt(i) - s.charAt(i - 1)).append("#");
    return sb.toString();
  }

    public boolean isCorrect(String[] words, String output) {
        String expected = oddString(words);
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
