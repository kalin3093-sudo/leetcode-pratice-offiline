import java.util.*;
class Marker {
  public int maxLengthBetweenEqualCharacters(String s) {
    int ans = -1;
    int[] lastSeen = new int[26];
    Arrays.fill(lastSeen, -1);

    for (int i = 0; i < s.length(); ++i) {
      final int c = s.charAt(i) - 'a';
      if (lastSeen[c] == -1)
        lastSeen[c] = i;
      else
        ans = Math.max(ans, i - lastSeen[c] - 1);
    }

    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = maxLengthBetweenEqualCharacters(s);
        return expected == output;
    }
}
