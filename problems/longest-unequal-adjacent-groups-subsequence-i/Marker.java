import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<String> getLongestSubsequence(String[] words, int[] groups) {
    List<String> ans = new ArrayList<>();
    int groupId = -1;

    for (int i = 0; i < words.length; ++i)
      if (groups[i] != groupId) {
        groupId = groups[i];
        ans.add(words[i]);
      }

    return ans;
  }

    public boolean isCorrect(String[] words, int[] groups, List<String> output) {
        List<String> expected = getLongestSubsequence(words, groups);
        return expected.equals(output);
    }
}
