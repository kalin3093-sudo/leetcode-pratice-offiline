import java.util.*;
class Marker {
  public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
    final int m = rowSum.length;
    final int n = colSum.length;
    int[][] ans = new int[m][n];

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j) {
        ans[i][j] = Math.min(rowSum[i], colSum[j]);
        rowSum[i] -= ans[i][j];
        colSum[j] -= ans[i][j];
      }

    return ans;
  }

    public boolean isCorrect(int[] rowSum, int[] colSum, int[][] output) {
        int m = rowSum.length;
        int n = colSum.length;
        if (output == null || output.length != m) return false;
        long[] rowLeft = new long[m];
        long[] colLeft = new long[n];
        for (int i = 0; i < m; i++) rowLeft[i] = rowSum[i];
        for (int j = 0; j < n; j++) colLeft[j] = colSum[j];
        for (int i = 0; i < m; i++) {
            if (output[i] == null || output[i].length != n) return false;
            for (int j = 0; j < n; j++) {
                int v = output[i][j];
                if (v < 0) return false;
                rowLeft[i] -= v;
                colLeft[j] -= v;
            }
        }
        for (int i = 0; i < m; i++) if (rowLeft[i] != 0) return false;
        for (int j = 0; j < n; j++) if (colLeft[j] != 0) return false;
        return true;
    }
}
