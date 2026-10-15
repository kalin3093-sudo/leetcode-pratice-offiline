import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maximumXorProduct(long a, long b, int n) {
    final int MOD = 1_000_000_007;
    if (n > 0)
      for (long bit = 1L << (n - 1); bit > 0; bit >>= 1)
        // Pick a bit if it makes Math.min(a, b) larger.
        if ((Math.min(a, b) & bit) == 0) {
          a ^= bit;
          b ^= bit;
        }
    return (int) (a % MOD * (b % MOD) % MOD);
  }

    public boolean isCorrect(long a, long b, int n, int output) {
        int expected = maximumXorProduct(a, b, n);
        return expected == output;
    }
}
