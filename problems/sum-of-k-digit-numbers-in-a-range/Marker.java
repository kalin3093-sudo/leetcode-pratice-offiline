import java.util.*;

class Marker {
  private static final long MOD = 1000000007L;
  private static final long INV9 = 111111112L;

  public int sumOfNumbers(int l, int r, int k) {
    long count = r - l + 1;
    long digitSum = (long) (l + r) * count / 2 % MOD;
    long ans = digitSum * modPow(count, k - 1, MOD) % MOD;
    long pow10 = modPow(10, k, MOD);
    long repunit = (pow10 - 1 + MOD) % MOD * INV9 % MOD;
    ans = ans * repunit % MOD;
    return (int) ans;
  }

  public boolean isCorrect(int l, int r, int k, int output) {
    return sumOfNumbers(l, r, k) == output;
  }

  private long modPow(long base, long exp, long mod) {
    long result = 1 % mod;
    base %= mod;
    while (exp > 0) {
      if ((exp & 1) == 1) result = result * base % mod;
      base = base * base % mod;
      exp >>= 1;
    }
    return result;
  }
}
