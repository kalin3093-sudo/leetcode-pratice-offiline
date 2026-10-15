import java.util.*;
class Marker {
  public String shortestPalindrome(String s) {
    final String t = new StringBuilder(s).reverse().toString();

    for (int i = 0; i < t.length(); ++i)
      if (s.startsWith(t.substring(i)))
        return t.substring(0, i) + s;

    return t + s;
  }

    public boolean isCorrect(String s, String output) {
        String expected = shortestPalindrome(s);
        return expected.equals(output);
    }
}
