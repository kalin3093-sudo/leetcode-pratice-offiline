import java.util.*;

class Marker {
  public long kMirror(int k, int n) {
    long sum = 0;
    int found = 0;
    for (int len = 1; found < n; len++) {
      int halfLen = (len + 1) / 2;
      long start = pow10(halfLen - 1);
      long end = pow10(halfLen) - 1;
      boolean even = len % 2 == 0;
      for (long h = start; h <= end && found < n; h++) {
        long pal = makePalindrome(h, even);
        if (pal < 0) break;
        if (isMirror(pal, k)) {
          sum += pal;
          found++;
        }
      }
    }
    return sum;
  }

  public boolean isCorrect(int k, int n, long output) {
    return kMirror(k, n) == output;
  }

  private long pow10(int e) {
    long v = 1;
    for (int i = 0; i < e; i++) v *= 10;
    return v;
  }

  private long makePalindrome(long half, boolean even) {
    long pal = half;
    if (!even) pal /= 10;
    while (pal > 0) {
      if (half > (Long.MAX_VALUE - pal % 10) / 10) return -1;
      half = half * 10 + pal % 10;
      pal /= 10;
    }
    return half;
  }

  private boolean isMirror(long v, int k) {
    int[] d = new int[64];
    int len = 0;
    long t = v;
    while (t > 0) {
      d[len++] = (int) (t % k);
      t /= k;
    }
    for (int i = 0, j = len - 1; i < j; i++, j--) {
      if (d[i] != d[j]) return false;
    }
    return true;
  }
}
