import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
    final int MAX = 2_000_000_000;
    List<Pair<Integer, Integer>>[] graph = new List[n];

    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      final int w = edge[2];
      if (w == -1)
        continue;
      graph[u].add(new Pair<>(v, w));
      graph[v].add(new Pair<>(u, w));
    }

    int distToDestination = dijkstra(graph, source, destination);
    if (distToDestination < target)
      return new int[0][];
    if (distToDestination == target) {
      // Change the weights of negative edges to an impossible value.
      for (int[] edge : edges)
        if (edge[2] == -1)
          edge[2] = MAX;
      return edges;
    }

    for (int i = 0; i < edges.length; ++i) {
      final int u = edges[i][0];
      final int v = edges[i][1];
      final int w = edges[i][2];
      if (w != -1)
        continue;
      edges[i][2] = 1;
      graph[u].add(new Pair<>(v, 1));
      graph[v].add(new Pair<>(u, 1));
      distToDestination = dijkstra(graph, source, destination);
      if (distToDestination <= target) {
        edges[i][2] += target - distToDestination;
        // Change the weights of negative edges to an impossible value.
        for (int j = i + 1; j < edges.length; ++j)
          if (edges[j][2] == -1)
            edges[j][2] = MAX;
        return edges;
      }
    }

    return new int[0][];
  }

  private int dijkstra(List<Pair<Integer, Integer>>[] graph, int src, int dst) {
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
        if (d + w < dist[v]) {
          dist[v] = d + w;
          minHeap.offer(new Pair<>(dist[v], v));
        }
      }
    }

    return dist[dst];
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int[][] edges, int source, int destination, int target, int[][] output) {
        if (edges == null || output == null) return false;
        if (output.length == 0) {
            int[][] copy = new int[edges.length][];
            for (int i = 0; i < edges.length; i++)
                copy[i] = edges[i].clone();
            try {
                return modifiedGraphEdges(n, copy, source, destination, target).length == 0;
            } catch (Exception e) {
                return false;
            }
        }
        if (output.length != edges.length) return false;
        boolean[] used = new boolean[output.length];
        for (int i = 0; i < output.length; i++) {
            if (output[i] == null || output[i].length != 3) return false;
            if (output[i][0] < 0 || output[i][0] >= n || output[i][1] < 0 || output[i][1] >= n) return false;
        }
        for (int[] e : edges)
            if (e[2] != -1 && !matchEdge(output, used, e, e[2])) return false;
        for (int[] e : edges)
            if (e[2] == -1 && !matchEdge(output, used, e, -1)) return false;
        for (boolean u : used)
            if (!u) return false;
        try {
            return shortestDist(n, output, source, destination) == (long) target;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean matchEdge(int[][] output, boolean[] used, int[] edge, int needW) {
        int u = Math.min(edge[0], edge[1]);
        int v = Math.max(edge[0], edge[1]);
        for (int i = 0; i < output.length; i++) {
            if (used[i]) continue;
            if (Math.min(output[i][0], output[i][1]) != u) continue;
            if (Math.max(output[i][0], output[i][1]) != v) continue;
            int w = output[i][2];
            if (needW == -1) {
                if (w < 1 || w > 2_000_000_000) continue;
            } else if (w != needW) {
                continue;
            }
            used[i] = true;
            return true;
        }
        return false;
    }

    private long shortestDist(int n, int[][] graph, int source, int destination) {
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();
        for (int[] e : graph) {
            g[e[0]].add(new int[] {e[1], e[2]});
            g[e[1]].add(new int[] {e[0], e[2]});
        }
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 4);
        dist[source] = 0;
        PriorityQueue<long[]> minHeap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        minHeap.offer(new long[] {0, source});
        while (!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            long d = top[0];
            int u = (int) top[1];
            if (d > dist[u]) continue;
            for (int[] e : g[u]) {
                int v = e[0];
                long w = e[1];
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    minHeap.offer(new long[] {dist[v], v});
                }
            }
        }
        return dist[destination];
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
