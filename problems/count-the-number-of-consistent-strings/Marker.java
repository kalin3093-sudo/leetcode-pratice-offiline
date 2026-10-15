import java.util.*;
class Marker {
  public int countConsistentStrings(String allowed, String[] words) {
    return (int) Arrays.stream(words)
        .filter(word -> word.matches(String.format("[%s]*", allowed)))
        .count();
  }

    public boolean isCorrect(String allowed, String[] words, int output) {
        int expected = countConsistentStrings(allowed, words);
        return expected == output;
    }
}
