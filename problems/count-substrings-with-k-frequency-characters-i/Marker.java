import java.util.*;
class Marker {
  public int numberOfSubstrings(String s, int k) {
    final int n = s.length();
    int ans = n * (n + 1) / 2;
    int[] count = new int[26];

    for (int l = 0, r = 0; r < n; ++r) {
      final int c = s.charAt(r) - 'a';
      ++count[c];
      while (count[c] == k)
        --count[s.charAt(l++) - 'a'];
      ans -= r - l + 1;
    }

    return ans;
  }

    public boolean isCorrect(String s, int k, int output) {
        int expected = numberOfSubstrings(s, k);
        return expected == output;
    }
}
