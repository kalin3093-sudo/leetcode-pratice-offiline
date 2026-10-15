import java.util.*;
class Marker {
  public boolean isThree(int n) {
    if (n == 1)
      return false;
    // The numbers with exactly three divisors are perfect squares of a prime
    // number.
    final int root = (int) Math.sqrt(n);
    return root * root == n && isPrime(root);
  }

  private boolean isPrime(int num) {
    for (int i = 2; i <= Math.sqrt(num); ++i)
      if (num % i == 0)
        return false;
    return true;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = isThree(n);
        return expected == output;
    }
}
