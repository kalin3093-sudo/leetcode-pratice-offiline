import java.util.*;
class Marker {
  public int minimumOperationsToMakeKPeriodic(String word, int k) {
    Map<String, Integer> count = new HashMap<>();

    for (int i = 0; i < word.length(); i += k)
      count.merge(word.substring(i, i + k), 1, Integer::sum);

    final int maxFreq = count.values().stream().max(Integer::compare).orElse(0);
    return word.length() / k - maxFreq;
  }

    public boolean isCorrect(String word, int k, int output) {
        int expected = minimumOperationsToMakeKPeriodic(word, k);
        return expected == output;
    }
}
