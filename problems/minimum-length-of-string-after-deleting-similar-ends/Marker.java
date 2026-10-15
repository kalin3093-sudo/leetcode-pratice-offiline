import java.util.*;
class Marker {
  public int minimumLength(String s) {
    int i = 0;
    int j = s.length() - 1;

    while (i < j && s.charAt(i) == s.charAt(j)) {
      final char c = s.charAt(i);
      while (i <= j && s.charAt(i) == c)
        ++i;
      while (i <= j && s.charAt(j) == c)
        --j;
    }

    return j - i + 1;
  }

    public boolean isCorrect(String s, int output) {
        int expected = minimumLength(s);
        return expected == output;
    }
}
