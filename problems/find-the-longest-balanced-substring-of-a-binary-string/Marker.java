import java.util.*;
class Marker {
  public int findTheLongestBalancedSubstring(String s) {
    int ans = 0;

    for (int i = 0; i < s.length();) {
      int zeros = 0;
      int ones = 0;
      while (i < s.length() && s.charAt(i) == '0') {
        ++zeros;
        ++i;
      }
      while (i < s.length() && s.charAt(i) == '1') {
        ++ones;
        ++i;
      }
      ans = Math.max(ans, Math.min(zeros, ones));
    }

    return ans * 2;
  }

    public boolean isCorrect(String s, int output) {
        int expected = findTheLongestBalancedSubstring(s);
        return expected == output;
    }
}
