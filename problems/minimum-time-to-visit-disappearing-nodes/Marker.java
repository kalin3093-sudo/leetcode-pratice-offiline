import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] minimumTime(int n, int[][] edges, int[] disappear) {
    List<Pair<Integer, Integer>>[] graph = new List[n];

    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      final int w = edge[2];
      graph[u].add(new Pair<>(v, w));
      graph[v].add(new Pair<>(u, w));
    }

    return dijkstra(graph, 0, disappear);
  }

  private int[] dijkstra(List<Pair<Integer, Integer>>[] graph, int src, int[] disappear) {
    int[] dist = new int[graph.length];
    Arrays.fill(dist, Integer.MAX_VALUE);

    dist[src] = 0;
    Queue<Pair<Integer, Integer>> minHeap =
        new PriorityQueue<>(Comparator.comparingInt(Pair::getKey)) {
          { offer(new Pair<>(dist[src], src)); } // (d, u)
        };

    while (!minHeap.isEmpty()) {
      final int d = minHeap.peek().getKey();
      final int u = minHeap.poll().getValue();
      if (d > dist[u])
        continue;
      for (Pair<Integer, Integer> pair : graph[u]) {
        final int v = pair.getKey();
        final int w = pair.getValue();
        if (d + w < disappear[v] && d + w < dist[v]) {
          dist[v] = d + w;
          minHeap.offer(new Pair<>(dist[v], v));
        }
      }
    }

    for (int i = 0; i < dist.length; ++i)
      if (dist[i] == Integer.MAX_VALUE)
        dist[i] = -1;

    return dist;
  }

    public boolean isCorrect(int n, int[][] edges, int[] disappear, int[] output) {
        int[] expected = minimumTime(n, edges, disappear);
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
