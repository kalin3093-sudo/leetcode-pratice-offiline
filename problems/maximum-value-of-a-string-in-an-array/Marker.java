import java.util.*;
class Marker {
  public int maximumValue(String[] strs) {
    int ans = 0;
    for (final String s : strs)
      ans = Math.max(ans, s.chars().anyMatch(c -> Character.isAlphabetic(c)) ? s.length()
                                                                             : Integer.valueOf(s));
    return ans;
  }

    public boolean isCorrect(String[] strs, int output) {
        int expected = maximumValue(strs);
        return expected == output;
    }
}
