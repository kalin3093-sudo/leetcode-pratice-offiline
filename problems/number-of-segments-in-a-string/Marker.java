import java.util.*;
class Marker {
  public int countSegments(String s) {
    int ans = 0;

    for (int i = 0; i < s.length(); ++i)
      if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' '))
        ++ans;

    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = countSegments(s);
        return expected == output;
    }
}
