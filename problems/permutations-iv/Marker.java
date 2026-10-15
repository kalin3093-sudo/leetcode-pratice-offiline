import java.util.*;

class Marker {
  public int[] permute(int n, long k) {
    long[] fact = new long[n + 1];
    fact[0] = 1;
    for (int i = 1; i <= n; i++)
      fact[i] = (fact[i - 1] > Long.MAX_VALUE / i) ? Long.MAX_VALUE : fact[i - 1] * i;
    int odds = (n + 1) / 2, evens = n / 2;
    long total = 0;
    for (int c = 1; c <= n; c++) {
      long comp = completions(n, fact, 0, odds, evens, c % 2 == 1);
      total = (total > Long.MAX_VALUE - comp) ? Long.MAX_VALUE : total + comp;
    }
    if (k > total)
      return new int[0];
    boolean[] used = new boolean[n + 1];
    int[] res = new int[n];
    int o = odds, e = evens;
    for (int i = 0; i < n; i++) {
      boolean picked = false;
      for (int c = 1; c <= n; c++) {
        if (used[c])
          continue;
        boolean odd = c % 2 == 1;
        if (i > 0 && odd == (res[i - 1] % 2 == 1))
          continue;
        long comp = completions(n, fact, i, o, e, odd);
        if (k > comp) {
          k -= comp;
          continue;
        }
        res[i] = c;
        used[c] = true;
        if (odd)
          o--;
        else
          e--;
        picked = true;
        break;
      }
      if (!picked)
        return new int[0];
    }
    return res;
  }

  private long completions(int n, long[] fact, int i, int o, int e, boolean odd) {
    int oo = o - (odd ? 1 : 0);
    int ee = e - (odd ? 0 : 1);
    if (oo < 0 || ee < 0)
      return 0;
    int len = n - 1 - i;
    int oddCount = (!odd) ? (len + 1) / 2 : len / 2;
    if (oo != oddCount)
      return 0;
    long a = fact[oo], b = fact[ee];
    return (a > Long.MAX_VALUE / b) ? Long.MAX_VALUE : a * b;
  }

  public boolean isCorrect(int n, long k, int[] output) {
    return output != null && Arrays.equals(permute(n, k), output);
  }
}
