import java.util.*;
class Marker {
  public int numberOfAlternatingGroups(int[] colors, int k) {
    final int n = colors.length;
    int ans = 0;
    int alternating = 1;

    for (int i = 0; i < n + k - 2; ++i) {
      alternating = colors[i % n] == colors[(i - 1 + n) % n] ? 1 : alternating + 1;
      if (alternating >= k)
        ++ans;
    }

    return ans;
  }

    public boolean isCorrect(int[] colors, int k, int output) {
        int expected = numberOfAlternatingGroups(colors, k);
        return expected == output;
    }
}
