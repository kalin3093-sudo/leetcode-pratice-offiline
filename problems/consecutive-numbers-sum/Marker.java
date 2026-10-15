import java.util.*;
class Marker {
  public int consecutiveNumbersSum(int n) {
    int ans = 0;
    for (int i = 1, triangleNum = i; triangleNum <= n; ++i, triangleNum += i)
      if ((n - triangleNum) % i == 0)
        ++ans;
    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = consecutiveNumbersSum(n);
        return expected == output;
    }
}
