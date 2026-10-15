import java.util.*;
class Marker {
  public int sumOfMultiples(int n) {
    int ans = 0;
    for (int i = 1; i <= n; ++i)
      if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0)
        ans += i;
    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = sumOfMultiples(n);
        return expected == output;
    }
}
