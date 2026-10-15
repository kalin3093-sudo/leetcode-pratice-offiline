import java.util.*;
class Marker {
  public int maximumNumberOfStringPairs(String[] words) {
    int ans = 0;
    boolean[] seen = new boolean[26 * 26];

    for (final String word : words) {
      if (seen[val(word.charAt(1)) * 26 + val(word.charAt(0))])
        ++ans;
      seen[val(word.charAt(0)) * 26 + val(word.charAt(1))] = true;
    }

    return ans;
  }

  private final int val(char c) {
    return c - 'a';
  }

    public boolean isCorrect(String[] words, int output) {
        int expected = maximumNumberOfStringPairs(words);
        return expected == output;
    }
}
