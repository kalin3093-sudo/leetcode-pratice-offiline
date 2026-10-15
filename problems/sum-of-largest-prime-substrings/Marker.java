import java.util.*;

class Marker {
  public long sumOfLargestPrimes(String s) {
    Set<Long> primes = new HashSet<>();
    int n = s.length();
    for (int i = 0; i < n; i++) {
      long v = 0;
      for (int j = i; j < n; j++) {
        v = v * 10 + (s.charAt(j) - '0');
        if (isPrime(v)) primes.add(v);
      }
    }
    List<Long> list = new ArrayList<>(primes);
    Collections.sort(list);
    Collections.reverse(list);
    long sum = 0;
    for (int i = 0; i < Math.min(3, list.size()); i++) sum += list.get(i);
    return sum;
  }

  public boolean isCorrect(String s, long output) {
    return sumOfLargestPrimes(s) == output;
  }

  private boolean isPrime(long v) {
    if (v < 2) return false;
    if (v % 2 == 0) return v == 2;
    for (long d = 3; d * d <= v; d += 2) {
      if (v % d == 0) return false;
    }
    return true;
  }
}
