import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int nearestExit(char[][] maze, int[] entrance) {
    final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    final int m = maze.length;
    final int n = maze[0].length;
    Queue<Pair<Integer, Integer>> q =
        new ArrayDeque<>(List.of(new Pair<>(entrance[0], entrance[1])));
    boolean[][] seen = new boolean[m][n];
    seen[entrance[0]][entrance[1]] = true;

    for (int step = 1; !q.isEmpty(); ++step)
      for (int sz = q.size(); sz > 0; --sz) {
        final int i = q.peek().getKey();
        final int j = q.poll().getValue();
        for (int[] dir : DIRS) {
          final int x = i + dir[0];
          final int y = j + dir[1];
          if (x < 0 || x == m || y < 0 || y == n)
            continue;
          if (seen[x][y] || maze[x][y] == '+')
            continue;
          if (x == 0 || x == m - 1 || y == 0 || y == n - 1)
            return step;
          q.offer(new Pair<>(x, y));
          seen[x][y] = true;
        }
      }

    return -1;
  }

    public boolean isCorrect(char[][] maze, int[] entrance, int output) {
        int expected = nearestExit(maze, entrance);
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
