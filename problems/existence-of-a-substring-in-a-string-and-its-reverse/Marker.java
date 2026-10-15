import java.util.*;
class Marker {
  public boolean isSubstringPresent(String s) {
    String reversed = new StringBuilder(s).reverse().toString();
    for (int i = 0; i + 2 <= s.length(); ++i)
      if (reversed.contains(s.substring(i, i + 2)))
        return true;
    return false;
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = isSubstringPresent(s);
        return expected == output;
    }
}
