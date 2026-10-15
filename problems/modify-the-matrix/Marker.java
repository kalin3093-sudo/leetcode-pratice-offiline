import java.util.*;
class Marker {
  public int[][] modifiedMatrix(int[][] matrix) {
    final int m = matrix.length;
    final int n = matrix[0].length;
    int[][] ans = matrix.clone();

    for (int j = 0; j < n; ++j) {
      int mx = 0;
      for (int i = 0; i < m; ++i)
        mx = Math.max(mx, matrix[i][j]);
      for (int i = 0; i < m; ++i)
        if (matrix[i][j] == -1)
          ans[i][j] = mx;
    }

    return ans;
  }

    public boolean isCorrect(int[][] matrix, int[][] output) {
        int[][] expected = modifiedMatrix(matrix);
        return Arrays.deepEquals(expected, output);
    }
}
