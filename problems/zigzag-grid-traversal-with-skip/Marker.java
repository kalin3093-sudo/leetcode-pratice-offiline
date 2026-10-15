import java.util.*;
class Marker {
  public List<Integer> zigzagTraversal(int[][] grid) {
    final int m = grid.length;
    final int n = grid[0].length;
    List<Integer> ans = new ArrayList<>();
    List<Integer> zigzag = new ArrayList<>();

    for (int i = 0; i < m; ++i)
      if (i % 2 == 0) {
        for (int j = 0; j < n; ++j)
          zigzag.add(grid[i][j]);
      } else {
        for (int j = n - 1; j >= 0; --j)
          zigzag.add(grid[i][j]);
      }

    for (int i = 0; i < zigzag.size(); i += 2)
      ans.add(zigzag.get(i));

    return ans;
  }

    public boolean isCorrect(int[][] grid, List<Integer> output) {
        List<Integer> expected = zigzagTraversal(grid);
        return expected.equals(output);
    }
}
