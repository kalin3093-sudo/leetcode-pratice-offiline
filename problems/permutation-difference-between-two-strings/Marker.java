import java.util.*;
class Marker {
  public int findPermutationDifference(String s, String t) {
    int ans = 0;
    int[] indices = new int[26];

    for (int i = 0; i < s.length(); ++i)
      indices[s.charAt(i) - 'a'] = i;

    for (int i = 0; i < t.length(); ++i)
      ans += Math.abs(indices[t.charAt(i) - 'a'] - i);

    return ans;
  }

    public boolean isCorrect(String s, String t, int output) {
        int expected = findPermutationDifference(s, t);
        return expected == output;
    }
}
