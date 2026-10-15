import java.util.*;
class Marker {
  public int equalPairs(int[][] grid) {
    final int n = grid.length;
    int ans = 0;

    for (int i = 0; i < n; ++i)
      for (int j = 0; j < n; ++j) {
        int k = 0;
        for (; k < n; ++k)
          if (grid[i][k] != grid[k][j])
            break;
        if (k == n) // R[i] == C[j]
          ++ans;
      }

    return ans;
  }

    public boolean isCorrect(int[][] grid, int output) {
        int expected = equalPairs(grid);
        return expected == output;
    }
}
