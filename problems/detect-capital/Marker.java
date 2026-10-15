import java.util.*;
class Marker {
  public boolean detectCapitalUse(String word) {
    return word.equals(word.toUpperCase()) ||
        word.substring(1).equals(word.substring(1).toLowerCase());
  }

    public boolean isCorrect(String word, boolean output) {
        boolean expected = detectCapitalUse(word);
        return expected == output;
    }
}
