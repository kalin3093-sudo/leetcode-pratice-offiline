import java.util.*;
class Marker {
  public int reverseDegree(String s) {
    int ans = 0;
    for (int i = 0; i < s.length(); ++i) {
      final int reversePos = 26 - (s.charAt(i) - 'a');
      ans += reversePos * (i + 1);
    }
    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = reverseDegree(s);
        return expected == output;
    }
}
