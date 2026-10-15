import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int wordCount(String[] startWords, String[] targetWords) {
    int ans = 0;
    Set<Integer> seen = Arrays.stream(startWords).map(this::getMask).collect(Collectors.toSet());

    for (final String w : targetWords) {
      final int mask = getMask(w);
      for (final char c : w.toCharArray())
        // Toggle one character.
        if (seen.contains(mask ^ 1 << c - 'a')) {
          ++ans;
          break;
        }
    }

    return ans;
  }

  private int getMask(final String s) {
    int mask = 0;
    for (final char c : s.toCharArray())
      mask ^= 1 << c - 'a';
    return mask;
  }

    public boolean isCorrect(String[] startWords, String[] targetWords, int output) {
        int expected = wordCount(startWords, targetWords);
        return expected == output;
    }
}
