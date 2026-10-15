import java.util.*;
class Marker {
  public long minimumCost(String s) {
    final int n = s.length();
    long ans = 0;

    for (int i = 1; i < n; ++i)
      if (s.charAt(i) != s.charAt(i - 1))
        // Invert s[0..i - 1] or s[i..n - 1].
        ans += Math.min(i, n - i);

    return ans;
  }

    public boolean isCorrect(String s, long output) {
        long expected = minimumCost(s);
        return expected == output;
    }
}
