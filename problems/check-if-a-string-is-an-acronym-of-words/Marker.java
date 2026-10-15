import java.util.*;
class Marker {
  public boolean isAcronym(List<String> words, String s) {
    if (words.size() != s.length())
      return false;

    for (int i = 0; i < words.size(); ++i)
      if (words.get(i).charAt(0) != s.charAt(i))
        return false;

    return true;
  }

    public boolean isCorrect(List<String> words, String s, boolean output) {
        boolean expected = isAcronym(words, s);
        return expected == output;
    }
}
