import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  // You need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int ans = 0;

    for (int i = 0; i < 32; ++i)
      if (((n >> i) & 1) == 1)
        ++ans;

    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = hammingWeight(n);
        return expected == output;
    }
}
