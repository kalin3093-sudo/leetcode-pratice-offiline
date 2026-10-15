import java.util.*;
class Marker {
  public int minSteps(String s, String t) {
    int[] count = new int[26];

    for (final char c : s.toCharArray())
      ++count[c - 'a'];

    for (final char c : t.toCharArray())
      --count[c - 'a'];

    return Arrays.stream(count).map(Math::abs).sum() / 2;
  }

    public boolean isCorrect(String s, String t, int output) {
        int expected = minSteps(s, t);
        return expected == output;
    }
}
