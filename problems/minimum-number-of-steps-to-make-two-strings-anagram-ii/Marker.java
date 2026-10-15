import java.util.*;
class Marker {
  public int minSteps(String s, String t) {
    int[] count = new int[26];
    s.chars().forEach(c -> ++count[c - 'a']);
    t.chars().forEach(c -> --count[c - 'a']);
    return Arrays.stream(count).map(Math::abs).sum();
  }

    public boolean isCorrect(String s, String t, int output) {
        int expected = minSteps(s, t);
        return expected == output;
    }
}
