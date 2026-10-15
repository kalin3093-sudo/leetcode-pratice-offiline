import java.util.*;
class Marker {
  public int xorOperation(int n, int start) {
    int ans = 0;
    for (int i = 0; i < n; ++i)
      ans ^= start + 2 * i;
    return ans;
  }

    public boolean isCorrect(int n, int start, int output) {
        int expected = xorOperation(n, start);
        return expected == output;
    }
}
