import java.util.*;
class Marker {
  public int projectionArea(int[][] grid) {
    int ans = 0;

    for (int i = 0; i < grid.length; ++i) {
      int maxOfRow = 0;
      int maxOfCol = 0;
      for (int j = 0; j < grid.length; ++j) {
        maxOfRow = Math.max(maxOfRow, grid[i][j]);
        maxOfCol = Math.max(maxOfCol, grid[j][i]);
        if (grid[i][j] > 0)
          ++ans;
      }
      ans += maxOfRow + maxOfCol;
    }

    return ans;
  }

    public boolean isCorrect(int[][] grid, int output) {
        int expected = projectionArea(grid);
        return expected == output;
    }
}
