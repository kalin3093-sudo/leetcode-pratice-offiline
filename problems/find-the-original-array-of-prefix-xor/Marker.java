import java.util.*;
class Marker {
  public int[] findArray(int[] pref) {
    int[] ans = new int[pref.length];

    ans[0] = pref[0];
    for (int i = 1; i < ans.length; ++i)
      ans[i] = pref[i] ^ pref[i - 1];

    return ans;
  }

    public boolean isCorrect(int[] pref, int[] output) {
        int[] expected = findArray(pref);
        return Arrays.equals(expected, output);
    }
}
