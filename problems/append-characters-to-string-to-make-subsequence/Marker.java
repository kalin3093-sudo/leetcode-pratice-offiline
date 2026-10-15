import java.util.*;
class Marker {
  public int appendCharacters(String s, String t) {
    int i = 0; // t's index

    for (final char c : s.toCharArray())
      if (c == t.charAt(i))
        if (++i == t.length())
          return 0;

    return t.length() - i;
  }

    public boolean isCorrect(String s, String t, int output) {
        int expected = appendCharacters(s, t);
        return expected == output;
    }
}
