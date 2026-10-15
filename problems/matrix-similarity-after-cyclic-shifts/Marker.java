import java.util.*;
class Marker {
  public boolean areSimilar(int[][] mat, int k) {
    final int n = mat[0].length;
    for (int[] row : mat)
      for (int j = 0; j < n; ++j)
        if (row[j] != row[(j + k) % n])
          return false;
    return true;
  }

    public boolean isCorrect(int[][] mat, int k, boolean output) {
        boolean expected = areSimilar(mat, k);
        return expected == output;
    }
}
