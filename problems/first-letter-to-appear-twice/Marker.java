import java.util.*;
class Marker {
  public char repeatedCharacter(String s) {
    boolean[] seen = new boolean[26];

    for (final char c : s.toCharArray()) {
      if (seen[c - 'a'])
        return c;
      seen[c - 'a'] = true;
    }

    throw new IllegalArgumentException();
  }

    public boolean isCorrect(String s, char output) {
        char expected = repeatedCharacter(s);
        return expected == output;
    }
}
