import java.util.*;
class Marker {
  public List<String> stringMatching(String[] words) {
    List<String> ans = new ArrayList<>();
    for (final String a : words)
      for (final String b : words)
        if (a.length() < b.length() && b.indexOf(a) != -1) {
          ans.add(a);
          break;
        }
    return ans;
  }

    public boolean isCorrect(String[] words, List<String> output) {
        List<String> expected = stringMatching(words);
        return expected.equals(output);
    }
}
