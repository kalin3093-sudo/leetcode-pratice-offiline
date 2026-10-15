import java.util.*;

class Marker {
  public String reverseByType(String s) {
    List<Character> letters = new ArrayList<>();
    List<Character> specials = new ArrayList<>();
    for (char c : s.toCharArray()) {
      if (c >= 'a' && c <= 'z')
        letters.add(c);
      else
        specials.add(c);
    }
    char[] res = s.toCharArray();
    int li = letters.size() - 1, si = specials.size() - 1;
    for (int i = 0; i < res.length; i++) {
      if (res[i] >= 'a' && res[i] <= 'z')
        res[i] = letters.get(li--);
      else
        res[i] = specials.get(si--);
    }
    return new String(res);
  }

  public boolean isCorrect(String s, String output) {
    return output != null && output.equals(reverseByType(s));
  }
}
