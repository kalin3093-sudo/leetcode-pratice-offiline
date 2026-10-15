import java.util.*;
class Marker {
  public int prefixCount(String[] words, String pref) {
    return (int) Arrays.stream(words).filter(w -> w.startsWith(pref)).count();
  }

    public boolean isCorrect(String[] words, String pref, int output) {
        int expected = prefixCount(words, pref);
        return expected == output;
    }
}
