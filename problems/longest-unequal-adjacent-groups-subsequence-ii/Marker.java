import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
    List<String> ans = new ArrayList<>();
    // dp[i] := the length of the longest subsequence ending in `words[i]`
    int[] dp = new int[words.length];
    Arrays.fill(dp, 1);
    // prev[i] := the best index of words[i]
    int[] prev = new int[words.length];
    Arrays.fill(prev, -1);

    for (int i = 1; i < words.length; ++i)
      for (int j = 0; j < i; ++j) {
        if (groups[i] == groups[j])
          continue;
        if (words[i].length() != words[j].length())
          continue;
        if (hammingDist(words[i], words[j]) != 1)
          continue;
        if (dp[i] < dp[j] + 1) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }

    // Find the last index of the subsequence.
    int index = getMaxIndex(dp);
    while (index != -1) {
      ans.add(words[index]);
      index = prev[index];
    }

    Collections.reverse(ans);
    return ans;
  }

  private int hammingDist(final String s1, final String s2) {
    int dist = 0;
    for (int i = 0; i < s1.length(); ++i)
      if (s1.charAt(i) != s2.charAt(i))
        ++dist;
    return dist;
  }

  private int getMaxIndex(int[] dp) {
    int maxIndex = 0;
    for (int i = 0; i < dp.length; ++i)
      if (dp[i] > dp[maxIndex])
        maxIndex = i;
    return maxIndex;
  }

    public boolean isCorrect(String[] words, int[] groups, List<String> output) {
        // any maximum-length valid subsequence is acceptable, not just the first found
        if (output == null)
            return false;
        if (output.size() != getWordsInLongestSubsequence(words, groups).size())
            return false;
        List<Integer> picked = new ArrayList<>();
        int idx = 0;
        for (final String w : output) {
            while (idx < words.length && !words[idx].equals(w))
                ++idx;
            if (idx == words.length)
                return false;
            picked.add(idx);
            ++idx;
        }
        for (int i = 1; i < picked.size(); ++i) {
            final int a = picked.get(i - 1);
            final int b = picked.get(i);
            if (groups[a] == groups[b])
                return false;
            if (words[a].length() != words[b].length())
                return false;
            if (hammingDist(words[a], words[b]) != 1)
                return false;
        }
        return true;
    }
}
