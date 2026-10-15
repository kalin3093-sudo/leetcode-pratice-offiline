import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int robotSim(int[] commands, int[][] obstacles) {
    final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int ans = 0;
    int d = 0; // 0 := north, 1 := east, 2 := south, 3 := west
    int x = 0; // the start x
    int y = 0; // the start y
    Set<Pair<Integer, Integer>> obstaclesSet = new HashSet<>();

    for (int[] obstacle : obstacles) {
      final int x_ = obstacle[0];
      final int y_ = obstacle[1];
      obstaclesSet.add(new Pair<>(x_, y_));
    }

    for (final int command : commands) {
      if (command == -1) {
        d = (d + 1) % 4;
      } else if (command == -2) {
        d = (d + 3) % 4;
      } else {
        for (int step = 0; step < command; ++step) {
          if (obstaclesSet.contains(new Pair<>(x + DIRS[d][0], y + DIRS[d][1])))
            break;
          x += DIRS[d][0];
          y += DIRS[d][1];
        }
      }
      ans = Math.max(ans, x * x + y * y);
    }

    return ans;
  }

    public boolean isCorrect(int[] commands, int[][] obstacles, int output) {
        int expected = robotSim(commands, obstacles);
        return expected == output;
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(key, p.key) && Objects.equals(value, p.value);
    }
    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
