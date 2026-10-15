import java.util.*;
class Marker {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> ans = new ArrayList<>();
    int[] count = new int[128];
    int required = p.length();

    for (final char c : p.toCharArray())
      ++count[c];

    for (int l = 0, r = 0; r < s.length(); ++r) {
      if (--count[s.charAt(r)] >= 0)
        --required;
      while (required == 0) {
        if (r - l + 1 == p.length())
          ans.add(l);
        if (++count[s.charAt(l++)] > 0)
          ++required;
      }
    }

    return ans;
  }

    public boolean isCorrect(String s, String p, List<Integer> output) {
        List<Integer> expected = findAnagrams(s, p);
        return expected.equals(output);
    }
}
