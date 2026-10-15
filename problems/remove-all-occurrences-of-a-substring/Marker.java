import java.util.*;
class Marker {
  public String removeOccurrences(String s, String part) {
    final int n = s.length();
    final int k = part.length();

    StringBuilder sb = new StringBuilder(s);
    int j = 0; // sb's index

    for (int i = 0; i < n; ++i) {
      sb.setCharAt(j++, s.charAt(i));
      if (j >= k && sb.substring(j - k, j).toString().equals(part))
        j -= k;
    }

    return sb.substring(0, j).toString();
  }

    public boolean isCorrect(String s, String part, String output) {
        String expected = removeOccurrences(s, part);
        return expected.equals(output);
    }
}
