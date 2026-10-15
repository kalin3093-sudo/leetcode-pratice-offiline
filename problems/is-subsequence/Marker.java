import java.util.*;
class Marker {
  public boolean isSubsequence(String s, String t) {
    if (s.isEmpty())
      return true;

    int i = 0;
    for (final char c : t.toCharArray())
      if (s.charAt(i) == c && ++i == s.length())
        return true;

    return false;
  }

    public boolean isCorrect(String s, String t, boolean output) {
        boolean expected = isSubsequence(s, t);
        return expected == output;
    }
}
