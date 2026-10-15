import java.util.*;
class Marker {
  public int countNegatives(int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    int ans = 0;
    int i = m - 1;
    int j = 0;

    while (i >= 0 && j < n) {
      if (grid[i][j] < 0) {
        ans += n - j;
        --i;
      } else {
        ++j;
      }
    }

    return ans;
  }

    public boolean isCorrect(int[][] grid, int output) {
        int expected = countNegatives(grid);
        return expected == output;
    }
}
