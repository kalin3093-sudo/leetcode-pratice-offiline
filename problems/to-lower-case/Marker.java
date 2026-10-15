import java.util.*;
class Marker {
  public String toLowerCase(String str) {
    final int diff = 'A' - 'a';

    char[] ans = str.toCharArray();

    for (int i = 0; i < ans.length; ++i)
      if (ans[i] >= 'A' && ans[i] <= 'Z')
        ans[i] -= diff;

    return new String(ans);
  }

    public boolean isCorrect(String s, String output) {
        String expected = toLowerCase(s);
        return expected.equals(output);
    }
}
