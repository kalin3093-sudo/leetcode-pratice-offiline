import java.util.*;
class Marker {
  public int countKeyChanges(String s) {
    int ans = 0;
    for (int i = 1; i < s.length(); ++i)
      if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(i - 1)))
        ++ans;
    return ans;
  }

    public boolean isCorrect(String s, int output) {
        int expected = countKeyChanges(s);
        return expected == output;
    }
}
