import java.util.*;
class Marker {
  public boolean isPrefixString(String s, String[] words) {
    StringBuilder sb = new StringBuilder();
    for (final String word : words) {
      sb.append(word);
      // Do not call `toString()` unless the length matches.
      if (sb.length() == s.length() && sb.toString().equals(s))
        return true;
    }
    return false;
  }

    public boolean isCorrect(String s, String[] words, boolean output) {
        boolean expected = isPrefixString(s, words);
        return expected == output;
    }
}
