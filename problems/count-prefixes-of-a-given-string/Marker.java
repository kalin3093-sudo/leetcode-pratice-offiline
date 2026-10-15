import java.util.*;
class Marker {
  public int countPrefixes(String[] words, String s) {
    return (int) Arrays.stream(words).filter(word -> s.startsWith(word)).count();
  }

    public boolean isCorrect(String[] words, String s, int output) {
        int expected = countPrefixes(words, s);
        return expected == output;
    }
}
