import java.util.*;
class Marker {
  public String removeStars(String s) {
    StringBuilder sb = new StringBuilder();
    for (final char c : s.toCharArray())
      if (c == '*')
        sb.deleteCharAt(sb.length() - 1);
      else
        sb.append(c);
    return sb.toString();
  }

    public boolean isCorrect(String s, String output) {
        String expected = removeStars(s);
        return expected.equals(output);
    }
}
