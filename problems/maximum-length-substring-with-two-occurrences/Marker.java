import java.util.*;
class Marker {
  public int maximumLengthSubstring(String s) {
    int ans = 0;
    int[] count = new int[26];

    for (int l = 0, r = 0; r < s.length(); ++r) {
      ++count[s.charAt(r) - 'a'];
      while (count[s.charAt(r) - 'a'] > 2)
        --count[s.charAt(l++) - 'a'];
      ans = Math.max(ans, r - l + 1);
    }

    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = maximumLengthSubstring(s);
        return expected == output;
    }
}
