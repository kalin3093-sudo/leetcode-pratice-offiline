import java.util.*;
class Marker {
  public String lastNonEmptyString(String s) {
    StringBuilder sb = new StringBuilder();
    int[] count = new int[26];

    for (final char c : s.toCharArray())
      ++count[c - 'a'];

    final int maxFreq = Arrays.stream(count).max().getAsInt();

    for (int i = s.length() - 1; i >= 0; --i)
      if (count[s.charAt(i) - 'a']-- == maxFreq)
        sb.append(s.charAt(i));

    return sb.reverse().toString();
  }

    public boolean isCorrect(String s, String output) {
        String expected = lastNonEmptyString(s);
        return expected.equals(output);
    }
}
