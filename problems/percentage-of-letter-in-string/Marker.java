import java.util.*;
class Marker {
  public int percentageLetter(String s, char letter) {
    return 100 * (int) s.chars().filter(c -> c == letter).count() / s.length();
  }

    public boolean isCorrect(String s, char letter, int output) {
        int expected = percentageLetter(s, letter);
        return expected == output;
    }
}
