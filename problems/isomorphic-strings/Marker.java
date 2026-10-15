import java.util.*;
class Marker {
  public boolean isIsomorphic(String s, String t) {
    Map<Character, Integer> charToIndex_s = new HashMap<>();
    Map<Character, Integer> charToIndex_t = new HashMap<>();

    for (Integer i = 0; i < s.length(); ++i)
      if (charToIndex_s.put(s.charAt(i), i) != charToIndex_t.put(t.charAt(i), i))
        return false;

    return true;
  }

    public boolean isCorrect(String s, String t, boolean output) {
        boolean expected = isIsomorphic(s, t);
        return expected == output;
    }
}
