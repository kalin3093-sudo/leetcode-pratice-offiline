import java.util.*;
class Marker {
  public int diagonalSum(int[][] mat) {
    final int n = mat.length;
    int ans = 0;

    for (int i = 0; i < n; ++i) {
      ans += mat[i][i];
      ans += mat[n - 1 - i][i];
    }

    return n % 2 == 0 ? ans : ans - mat[n / 2][n / 2];
  }

    public boolean isCorrect(int[][] mat, int output) {
        int expected = diagonalSum(mat);
        return expected == output;
    }
}
