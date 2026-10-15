import java.util.*;

class Marker {
  public int maxSizeSlices(int[] slices) {
    int m = slices.length;
    int need = m / 3;
    return Math.max(solve(slices, 0, m - 1, need), solve(slices, 1, m, need));
  }

  private int solve(int[] a, int from, int to, int need) {
    int[] take = new int[need + 1];
    int[] skip = new int[need + 1];
    Arrays.fill(take, -1);
    Arrays.fill(skip, -1);
    skip[0] = 0;
    for (int i = from; i < to; i++) {
      int[] nt = new int[need + 1];
      int[] ns = new int[need + 1];
      Arrays.fill(nt, -1);
      Arrays.fill(ns, -1);
      for (int j = 0; j <= need; j++) {
        if (j > 0 && skip[j - 1] >= 0)
          nt[j] = skip[j - 1] + a[i];
        ns[j] = Math.max(take[j], skip[j]);
      }
      take = nt;
      skip = ns;
    }
    return Math.max(take[need], skip[need]);
  }

  public boolean isCorrect(int[] slices, int output) {
    return output == maxSizeSlices(slices);
  }
}
