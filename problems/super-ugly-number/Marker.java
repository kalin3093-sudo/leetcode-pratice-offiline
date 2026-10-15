import java.util.*;
class Marker {
  public int nthSuperUglyNumber(int n, int[] primes) {
    final int k = primes.length;
    int[] indices = new int[k];
    int[] uglyNums = new int[n];
    uglyNums[0] = 1;

    for (int i = 1; i < n; ++i) {
      int[] nexts = new int[k];
      for (int j = 0; j < k; ++j)
        nexts[j] = uglyNums[indices[j]] * primes[j];
      final int next = Arrays.stream(nexts).min().getAsInt();
      for (int j = 0; j < k; ++j)
        if (next == nexts[j])
          ++indices[j];
      uglyNums[i] = next;
    }

    return uglyNums[n - 1];
  }

    public boolean isCorrect(int n, int[] primes, int output) {
        int expected = nthSuperUglyNumber(n, primes);
        return expected == output;
    }
}
