import java.util.*;
class Marker {
  public int firstUniqChar(String s) {
    int[] count = new int[26];

    for (final char c : s.toCharArray())
      ++count[c - 'a'];

    for (int i = 0; i < s.length(); ++i)
      if (count[s.charAt(i) - 'a'] == 1)
        return i;

    return -1;
  }

    public boolean isCorrect(String s, int output) {
        int expected = firstUniqChar(s);
        return expected == output;
    }
}
