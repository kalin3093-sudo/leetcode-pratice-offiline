import java.util.*;

class Marker {
  public long totalScore(int hp, int[] damage, int[] requirement) {
    int n = damage.length;
    long[] pref = new long[n + 1];
    for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + damage[i];

    long[] comp = pref.clone();
    Arrays.sort(comp);
    int m = 0;
    for (int i = 0; i < comp.length; i++) {
      if (i == 0 || comp[i] != comp[i - 1]) comp[m++] = comp[i];
    }

    int[] bit = new int[m + 2];
    add(bit, rank(comp, pref[0]) + 1, m);
    long failing = 0;
    for (int i = 1; i <= n; i++) {
      long threshold = requirement[i - 1] - (long) hp + pref[i];
      failing += query(bit, lowerBound(comp, threshold));
      add(bit, rank(comp, pref[i]) + 1, m);
    }
    return (long) n * (n + 1) / 2 - failing;
  }

  public boolean isCorrect(int hp, int[] damage, int[] requirement, long output) {
    return totalScore(hp, damage, requirement) == output;
  }

  private int rank(long[] comp, long v) {
    return Arrays.binarySearch(comp, v);
  }

  private int lowerBound(long[] comp, long v) {
    int lo = 0;
    int hi = comp.length;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (comp[mid] < v) lo = mid + 1;
      else hi = mid;
    }
    return lo;
  }

  private void add(int[] bit, int i, int n) {
    while (i <= n) {
      bit[i]++;
      i += i & -i;
    }
  }

  private int query(int[] bit, int i) {
    int s = 0;
    while (i > 0) {
      s += bit[i];
      i -= i & -i;
    }
    return s;
  }
}
