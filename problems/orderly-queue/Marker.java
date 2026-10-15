import java.util.*;
class Marker {
  public String orderlyQueue(String s, int k) {
    if (k > 1) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);
      return String.valueOf(chars);
    }

    String ans = s;

    for (int i = 1; i < s.length(); ++i) {
      String t = s.substring(i) + s.substring(0, i);
      if (ans.compareTo(t) > 0)
        ans = t;
    }

    return ans;
  }

    public boolean isCorrect(String s, int k, String output) {
        String expected = orderlyQueue(s, k);
        return expected.equals(output);
    }
}
