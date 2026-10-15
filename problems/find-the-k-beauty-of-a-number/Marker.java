import java.util.*;
class Marker {
  public int divisorSubstrings(int num, int k) {
    final String s = String.valueOf(num);
    int ans = 0;

    for (int i = 0; i + k <= s.length(); ++i) {
      final int x = Integer.parseInt(s.substring(i, i + k));
      if (x != 0 && num % x == 0)
        ++ans;
    }

    return ans;
  }

    public boolean isCorrect(int num, int k, int output) {
        int expected = divisorSubstrings(num, k);
        return expected == output;
    }
}
