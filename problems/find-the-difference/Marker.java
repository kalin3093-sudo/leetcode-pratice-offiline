import java.util.*;
class Marker {
  public char findTheDifference(String s, String t) {
    final char sXors = (char) s.chars().reduce(0, (a, b) -> a ^ b);
    final char tXors = (char) t.chars().reduce(0, (a, b) -> a ^ b);
    return (char) (sXors ^ tXors);
  }

    public boolean isCorrect(String s, String t, char output) {
        char expected = findTheDifference(s, t);
        return expected == output;
    }
}
