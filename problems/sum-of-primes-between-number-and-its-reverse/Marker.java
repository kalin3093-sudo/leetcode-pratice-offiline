import java.util.*;

class Marker {
  public int sumOfPrimesInRange(int n) {
    int r = reverse(n);
    int lo = Math.min(n, r);
    int hi = Math.max(n, r);
    boolean[] composite = new boolean[hi + 1];
    for (int i = 2; (long) i * i <= hi; i++) {
      if (!composite[i]) {
        for (long j = (long) i * i; j <= hi; j += i) composite[(int) j] = true;
      }
    }
    int sum = 0;
    for (int i = Math.max(2, lo); i <= hi; i++) {
      if (!composite[i]) sum += i;
    }
    return sum;
  }

  public boolean isCorrect(int n, int output) {
    return sumOfPrimesInRange(n) == output;
  }

  private int reverse(int x) {
    int r = 0;
    while (x > 0) {
      r = r * 10 + x % 10;
      x /= 10;
    }
    return r;
  }
}
