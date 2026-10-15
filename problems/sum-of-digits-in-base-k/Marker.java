import java.util.*;
class Marker {
  public int sumBase(int n, int k) {
    int ans = 0;

    while (n > 0) {
      ans += n % k;
      n /= k;
    }

    return ans;
  }

    public boolean isCorrect(int n, int k, int output) {
        int expected = sumBase(n, k);
        return expected == output;
    }
}
