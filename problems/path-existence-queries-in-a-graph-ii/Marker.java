import java.util.*;

class Marker {
  public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    Integer[] ord = new Integer[n];
    for (int i = 0; i < n; i++)
      ord[i] = i;
    Arrays.sort(ord, (a, b) -> nums[a] - nums[b]);
    int[] val = new int[n];
    int[] posOf = new int[n];
    for (int i = 0; i < n; i++) {
      val[i] = nums[ord[i]];
      posOf[ord[i]] = i;
    }
    int[] comp = new int[n];
    for (int i = 1; i < n; i++)
      comp[i] = (val[i] - val[i - 1] <= maxDiff) ? comp[i - 1] : comp[i - 1] + 1;
    int[] R = new int[n];
    int r = 0;
    for (int i = 0; i < n; i++) {
      if (r < i)
        r = i;
      while (r + 1 < n && val[r + 1] - val[i] <= maxDiff)
        r++;
      R[i] = r;
    }
    int[] L = new int[n];
    int lo = n - 1;
    for (int i = n - 1; i >= 0; i--) {
      while (lo > 0 && val[i] - val[lo - 1] <= maxDiff)
        lo--;
      L[i] = lo;
    }
    int LOG = 17;
    int[][] upR = new int[LOG][n];
    int[][] upL = new int[LOG][n];
    for (int i = 0; i < n; i++) {
      upR[0][i] = R[i];
      upL[0][i] = L[i];
    }
    for (int j = 1; j < LOG; j++)
      for (int i = 0; i < n; i++) {
        upR[j][i] = upR[j - 1][upR[j - 1][i]];
        upL[j][i] = upL[j - 1][upL[j - 1][i]];
      }
    int[] ans = new int[queries.length];
    for (int qi = 0; qi < queries.length; qi++) {
      int u = queries[qi][0], v = queries[qi][1];
      if (u == v) {
        ans[qi] = 0;
        continue;
      }
      int a = posOf[u], b = posOf[v];
      if (comp[a] != comp[b]) {
        ans[qi] = -1;
        continue;
      }
      if (a < b) {
        if (R[a] >= b) {
          ans[qi] = 1;
          continue;
        }
        int cur = a, jumps = 0;
        for (int j = LOG - 1; j >= 0; j--) {
          int cand = upR[j][cur];
          if (cand < b) {
            cur = cand;
            jumps += 1 << j;
          }
        }
        ans[qi] = jumps + 1;
      } else {
        if (L[a] <= b) {
          ans[qi] = 1;
          continue;
        }
        int cur = a, jumps = 0;
        for (int j = LOG - 1; j >= 0; j--) {
          int cand = upL[j][cur];
          if (cand > b) {
            cur = cand;
            jumps += 1 << j;
          }
        }
        ans[qi] = jumps + 1;
      }
    }
    return ans;
  }

  public boolean isCorrect(int n, int[] nums, int maxDiff, int[][] queries, int[] output) {
    return output != null && Arrays.equals(pathExistenceQueries(n, nums, maxDiff, queries), output);
  }
}
