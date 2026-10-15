import java.util.*;
class Marker {
  public int concatenatedBinary(int n) {
    final int MOD = 1_000_000_007;
    long ans = 0;

    for (int i = 1; i <= n; ++i)
      ans = ((ans << numberOfBits(i)) % MOD + i) % MOD;

    return (int) ans;
  }

  private int numberOfBits(int n) {
    return (int) (Math.log(n) / Math.log(2)) + 1;
  }

    public boolean isCorrect(int n, int output) {
        int expected = concatenatedBinary(n);
        return expected == output;
    }
}
