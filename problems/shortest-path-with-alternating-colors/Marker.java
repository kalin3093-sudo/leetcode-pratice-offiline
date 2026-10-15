import java.util.*;
import java.util.stream.*;
import java.util.function.*;
enum Color { INIT, RED, BLUE }

class Marker {
  public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
    int[] ans = new int[n];
    Arrays.fill(ans, -1);
    // graph[u] := [(v, edgeColor)]
    List<Pair<Integer, Color>>[] graph = new List[n];
    // [(u, prevColor)]
    Queue<Pair<Integer, Color>> q = new ArrayDeque<>(List.of(new Pair<>(0, Color.INIT)));
    Arrays.setAll(graph, i -> new ArrayList<>());

    for (int[] edge : redEdges) {
      final int u = edge[0];
      final int v = edge[1];
      graph[u].add(new Pair<>(v, Color.RED));
    }

    for (int[] edge : blueEdges) {
      final int u = edge[0];
      final int v = edge[1];
      graph[u].add(new Pair<>(v, Color.BLUE));
    }

    for (int step = 0; !q.isEmpty(); ++step)
      for (int sz = q.size(); sz > 0; --sz) {
        final int u = q.peek().getKey();
        Color prevColor = q.poll().getValue();
        ans[u] = ans[u] == -1 ? step : ans[u];
        for (int i = 0; i < graph[u].size(); ++i) {
          Pair<Integer, Color> node = graph[u].get(i);
          final int v = node.getKey();
          Color edgeColor = node.getValue();
          if (v == -1 || edgeColor == prevColor)
            continue;
          q.add(new Pair<>(v, edgeColor));
          // Mark (u, v) as used.
          graph[u].set(i, new Pair<>(-1, edgeColor));
        }
      }

    return ans;
  }

    public boolean isCorrect(int n, int[][] redEdges, int[][] blueEdges, int[] output) {
        int[] expected = shortestAlternatingPaths(n, redEdges, blueEdges);
        return Arrays.equals(expected, output);
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
