import java.util.*;
import java.util.stream.*;
import java.util.function.*;
enum Direction { FORWARD, BACKWARD }

class Marker {
  public int minimumJumps(int[] forbidden, int a, int b, int x) {
    int furthest = x + a + b;
    Set<Integer> seenForward = new HashSet<>();
    Set<Integer> seenBackward = new HashSet<>();

    for (final int pos : forbidden) {
      seenForward.add(pos);
      seenBackward.add(pos);
      furthest = Math.max(furthest, pos + a + b);
    }

    // (direction, position)
    Queue<Pair<Direction, Integer>> q = new ArrayDeque<>(List.of(new Pair<>(Direction.FORWARD, 0)));

    for (int ans = 0; !q.isEmpty(); ++ans)
      for (int sz = q.size(); sz > 0; --sz) {
        Direction dir = q.peek().getKey();
        final int pos = q.poll().getValue();
        if (pos == x)
          return ans;
        final int forward = pos + a;
        final int backward = pos - b;
        if (forward <= furthest && seenForward.add(forward))
          q.offer(new Pair<>(Direction.FORWARD, forward));
        // It cannot jump backward twice in a row.
        if (dir == Direction.FORWARD && backward >= 0 && seenBackward.add(backward))
          q.offer(new Pair<>(Direction.BACKWARD, backward));
      }

    return -1;
  }

    public boolean isCorrect(int[] forbidden, int a, int b, int x, int output) {
        int expected = minimumJumps(forbidden, a, b, x);
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
