import java.util.*;
class Marker {
  public int[][] constructGridLayout(int n, int[][] edges) {
    List<Integer>[] graph = new ArrayList[n];

    for (int i = 0; i < n; i++)
      graph[i] = new ArrayList<>();

    for (int[] edge : edges) {
      final int u = edge[0];
      final int v = edge[1];
      graph[u].add(v);
      graph[v].add(u);
    }

    // Randomly choose a node with the minimum degree as the corner.
    int corner = 0;
    for (int i = 1; i < n; i++)
      if (graph[i].size() < graph[corner].size())
        corner = i;

    boolean[] seen = new boolean[n];
    seen[corner] = true;
    int[] firstRow = getFirstRow(graph, corner, seen);
    int cols = firstRow.length;
    int rows = n / cols;

    int[][] ans = new int[rows][cols];
    ans[0] = firstRow;

    for (int i = 1; i < rows; ++i)
      for (int j = 0; j < cols; ++j)
        for (final int v : graph[ans[i - 1][j]])
          if (!seen[v]) {
            ans[i][j] = v;
            seen[v] = true;
            break;
          }

    return ans;
  }

  private int[] getFirstRow(List<Integer>[] graph, int corner, boolean[] seen) {
    final int cornerDegree = graph[corner].size();
    List<Integer> row = new ArrayList<>(List.of(corner));
    // Continue appending neighbors until we hit another corner.
    while (row.size() == 1 || graph[row.get(row.size() - 1)].size() == cornerDegree + 1) {
      List<Integer> neighbors = graph[row.get(row.size() - 1)];
      Collections.sort(neighbors, (a, b) -> graph[a].size() - graph[b].size());
      for (final int v : neighbors)
        if (!seen[v] && (graph[v].size() == cornerDegree || graph[v].size() == cornerDegree + 1)) {
          row.add(v);
          seen[v] = true;
          break;
        }
    }
    return row.stream().mapToInt(Integer::intValue).toArray();
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int[][] edges, int[][] output) {
        if (output == null || output.length == 0) return false;
        int rows = output.length;
        int cols = output[0].length;
        if (cols == 0 || cols > n || rows > n || (long) rows * cols != n) return false;
        int[] posR = new int[n];
        int[] posC = new int[n];
        boolean[] seen = new boolean[n];
        for (int i = 0; i < rows; i++) {
            if (output[i].length != cols) return false;
            for (int j = 0; j < cols; j++) {
                int v = output[i][j];
                if (v < 0 || v >= n || seen[v]) return false;
                seen[v] = true;
                posR[v] = i;
                posC[v] = j;
            }
        }
        Set<Long> edgeSet = new HashSet<>();
        for (int[] e : edges)
            edgeSet.add(pairKey(e[0], e[1], n));
        // horizontally/vertically adjacent cells must be connected by an edge
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (j + 1 < cols && !edgeSet.contains(pairKey(output[i][j], output[i][j + 1], n)))
                    return false;
                if (i + 1 < rows && !edgeSet.contains(pairKey(output[i][j], output[i + 1][j], n)))
                    return false;
            }
        // every edge must connect adjacent cells
        for (int[] e : edges)
            if (Math.abs(posR[e[0]] - posR[e[1]]) + Math.abs(posC[e[0]] - posC[e[1]]) != 1)
                return false;
        return true;
    }

    private static long pairKey(int u, int v, int n) {
        int a = Math.min(u, v);
        int b = Math.max(u, v);
        return (long) a * n + b;
    }
}
