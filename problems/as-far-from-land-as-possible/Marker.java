import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maxDistance(int[][] grid) {
    final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    final int m = grid.length;
    final int n = grid[0].length;
    Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
    int water = 0;

    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j)
        if (grid[i][j] == 0)
          ++water;
        else
          q.offer(new Pair<>(i, j));

    if (water == 0 || water == m * n)
      return -1;

    int ans = 0;

    for (int d = 0; !q.isEmpty(); ++d)
      for (int sz = q.size(); sz > 0; --sz) {
        Pair<Integer, Integer> pair = q.poll();
        final int i = pair.getKey();
        final int j = pair.getValue();
        ans = d;
        for (int[] dir : DIRS) {
          final int x = i + dir[0];
          final int y = j + dir[1];
          if (x < 0 || x == m || y < 0 || y == n)
            continue;
          if (grid[x][y] > 0)
            continue;
          q.offer(new Pair<>(x, y));
          grid[x][y] = 2; // Mark as visited.
        }
      }

    return ans;
  }

    public boolean isCorrect(int[][] grid, int output) {
        int expected = maxDistance(grid);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
