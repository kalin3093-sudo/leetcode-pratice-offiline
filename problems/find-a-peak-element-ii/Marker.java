import java.util.*;
class Marker {
  public int[] findPeakGrid(int[][] mat) {
    int l = 0;
    int r = mat.length - 1;

    while (l < r) {
      final int m = (l + r) / 2;
      if (Arrays.stream(mat[m]).max().getAsInt() >= Arrays.stream(mat[m + 1]).max().getAsInt())
        r = m;
      else
        l = m + 1;
    }

    return new int[] {l, getMaxIndex(mat[l])};
  }

  private int getMaxIndex(int[] arr) {
    int[] res = {0, arr[0]};
    for (int i = 1; i < arr.length; ++i)
      if (arr[i] > res[1])
        res = new int[] {i, arr[i]};
    return res[0];
  }

    public boolean isCorrect(int[][] mat, int[] output) {
        int rows = mat.length;
        int cols = rows == 0 ? 0 : mat[0].length;
        if (output == null || output.length != 2) return false;
        int r = output[0], c = output[1];
        if (r < 0 || r >= rows || c < 0 || c >= cols) return false;
        int v = mat[r][c];
        if (r > 0 && mat[r - 1][c] > v) return false;
        if (r + 1 < rows && mat[r + 1][c] > v) return false;
        if (c > 0 && mat[r][c - 1] > v) return false;
        if (c + 1 < cols && mat[r][c + 1] > v) return false;
        return true;
    }
}
