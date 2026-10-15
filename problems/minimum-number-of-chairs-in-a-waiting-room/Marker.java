import java.util.*;
class Marker {
  public int minimumChairs(String s) {
    int ans = 0;
    int chairs = 0;

    for (final char c : s.toCharArray()) {
      chairs += c == 'E' ? 1 : -1;
      ans = Math.max(ans, chairs);
    }

    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = minimumChairs(s);
        return expected == output;
    }
}
