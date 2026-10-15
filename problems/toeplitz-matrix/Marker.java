import java.util.*;
class Marker {
  public boolean isToeplitzMatrix(int[][] matrix) {
    for (int i = 0; i + 1 < matrix.length; ++i)
      for (int j = 0; j + 1 < matrix[0].length; ++j)
        if (matrix[i][j] != matrix[i + 1][j + 1])
          return false;
    return true;
  }

    public boolean isCorrect(int[][] matrix, boolean output) {
        boolean expected = isToeplitzMatrix(matrix);
        return expected == output;
    }
}
