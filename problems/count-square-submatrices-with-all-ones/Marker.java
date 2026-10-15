import java.util.*;
class Marker {
  public int countSquares(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i)
      for (int j = 0; j < matrix[0].length; ++j)
        if (matrix[i][j] == 1 && i > 0 && j > 0)
          matrix[i][j] +=
              Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1]));
    return Arrays.stream(matrix).flatMapToInt(Arrays::stream).sum();
  }

    public boolean isCorrect(int[][] matrix, int output) {
        int expected = countSquares(matrix);
        return expected == output;
    }
}
