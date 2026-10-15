import java.util.*;
class Marker {
  public int minChanges(String s) {
    int ans = 0;

    for (int i = 0; i + 1 < s.length(); i += 2)
      if (s.charAt(i) != s.charAt(i + 1))
        ++ans;

    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = minChanges(s);
        return expected == output;
    }
}
