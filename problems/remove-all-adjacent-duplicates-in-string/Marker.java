import java.util.*;
class Marker {
  public String removeDuplicates(final String S) {
    StringBuilder sb = new StringBuilder();

    for (final char c : S.toCharArray()) {
      final int n = sb.length();
      if (n > 0 && sb.charAt(n - 1) == c)
        sb.deleteCharAt(n - 1);
      else
        sb.append(c);
    }

    return sb.toString();
  }

    public boolean isCorrect(String s, String output) {
        String expected = removeDuplicates(s);
        return expected.equals(output);
    }
}
