import java.util.*;
class Marker {
  public int reinitializePermutation(int n) {
    int ans = 0;
    int i = 1;

    do {
      if (i < n / 2)
        i = i * 2;
      else
        i = (i - n / 2) * 2 + 1;
      ++ans;
    } while (i != 1);

    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = reinitializePermutation(n);
        return expected == output;
    }
}
