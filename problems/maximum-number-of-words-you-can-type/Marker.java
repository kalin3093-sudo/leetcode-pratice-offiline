import java.util.*;
class Marker {
  public int canBeTypedWords(String text, String brokenLetters) {
    int ans = 0;
    boolean[] broken = new boolean[26];

    for (final char c : brokenLetters.toCharArray())
      broken[c - 'a'] = true;

    for (final String word : text.split(" "))
      ans += canBeTyped(word, broken);

    return ans;
  }

  private int canBeTyped(final String word, boolean[] broken) {
    for (final char c : word.toCharArray())
      if (broken[c - 'a'])
        return 0;
    return 1;
  }

    public boolean isCorrect(String text, String brokenLetters, int output) {
        int expected = canBeTypedWords(text, brokenLetters);
        return expected == output;
    }
}
