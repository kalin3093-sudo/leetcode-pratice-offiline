import java.util.*;
class Marker {
  public int[][] construct2DArray(int[] original, int m, int n) {
    if (original.length != m * n)
      return new int[][] {};

    int[][] ans = new int[m][n];

    for (int i = 0; i < original.length; ++i)
      ans[i / n][i % n] = original[i];

    return ans;
  }

    public boolean isCorrect(int[] original, int m, int n, int[][] output) {
        int[][] expected = construct2DArray(original, m, n);
        return Arrays.deepEquals(expected, output);
    }
}
