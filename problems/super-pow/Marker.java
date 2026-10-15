import java.util.*;

class Marker {
  public int superPow(int a, int[] b) {
    int mod = 1337;
    int result = 1;
    for (int digit : b) {
      result = (int) (modPow(result, 10, mod) * modPow(a % mod, digit, mod) % mod);
    }
    return result;
  }

  public boolean isCorrect(int a, int[] b, int output) {
    return superPow(a, b) == output;
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
