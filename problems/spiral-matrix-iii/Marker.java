import java.util.*;
class Marker {
  public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
    final int[] dx = {1, 0, -1, 0};
    final int[] dy = {0, 1, 0, -1};
    List<int[]> ans = new ArrayList<>(List.of(new int[] {rStart, cStart}));

    for (int i = 0; ans.size() < rows * cols; ++i)
      for (int step = 0; step < i / 2 + 1; ++step) {
        rStart += dy[i % 4];
        cStart += dx[i % 4];
        if (0 <= rStart && rStart < rows && 0 <= cStart && cStart < cols)
          ans.add(new int[] {rStart, cStart});
      }

    return ans.stream().toArray(int[][] ::new);
  }

    public boolean isCorrect(int rows, int cols, int rStart, int cStart, int[][] output) {
        int[][] expected = spiralMatrixIII(rows, cols, rStart, cStart);
        return Arrays.deepEquals(expected, output);
    }
}
