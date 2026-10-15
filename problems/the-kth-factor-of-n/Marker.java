import java.util.*;
class Marker {
  public int kthFactor(int n, int k) {
    // If i is a divisor of n, then n / i is also a divisor of n. So, we can
    // find all the divisors of n by processing the numbers <= sqrt(n).
    int factor = 1;
    int i = 0; // the i-th factor

    for (; factor * factor < n; ++factor)
      if (n % factor == 0 && ++i == k)
        return factor;

    for (factor = n / factor; factor >= 1; --factor)
      if (n % factor == 0 && ++i == k)
        return n / factor;

    return -1;
  }

    public boolean isCorrect(int n, int k, int output) {
        int expected = kthFactor(n, k);
        return expected == output;
    }
}
