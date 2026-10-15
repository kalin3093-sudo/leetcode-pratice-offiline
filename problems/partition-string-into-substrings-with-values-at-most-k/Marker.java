import java.util.*;
class Marker {
  public int minimumPartition(String s, int k) {
    int ans = 1;
    long curr = 0;

    for (final char c : s.toCharArray()) {
      curr = curr * 10 + c - '0';
      if (curr > k) {
        curr = c - '0';
        ++ans;
      }
      if (curr > k)
        return -1;
    }

    return ans;
  }

    public boolean isCorrect(String s, int k, int output) {
        int expected = minimumPartition(s, k);
        return expected == output;
    }
}
