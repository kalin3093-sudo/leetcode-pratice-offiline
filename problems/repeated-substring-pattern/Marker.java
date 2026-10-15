import java.util.*;
class Marker {
  public boolean repeatedSubstringPattern(String s) {
    final String ss = s + s;
    return ss.substring(1, ss.length() - 1).contains(s);
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = repeatedSubstringPattern(s);
        return expected == output;
    }
}
