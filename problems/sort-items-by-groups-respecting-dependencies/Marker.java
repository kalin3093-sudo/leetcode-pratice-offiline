import java.util.*;
class Marker {
  public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    List<Integer>[] graph = new List[n + m];
    int[] inDegrees = new int[n + m];

    for (int i = 0; i < graph.length; ++i)
      graph[i] = new ArrayList<>();

    // Build the graph by remapping the k-th group to k + n imaginary node.
    for (int i = 0; i < group.length; ++i) {
      if (group[i] == -1)
        continue;
      graph[group[i] + n].add(i);
      ++inDegrees[i];
    }

    for (int i = 0; i < beforeItems.size(); ++i)
      for (final int b : beforeItems.get(i)) {
        final int u = group[b] == -1 ? b : group[b] + n;
        final int v = group[i] == -1 ? i : group[i] + n;
        if (u == v) { // u and v are already in the same group.
          graph[b].add(i);
          ++inDegrees[i];
        } else {
          graph[u].add(v);
          ++inDegrees[v];
        }
      }

    // Perform topological sorting.
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < n + m; ++i)
      if (inDegrees[i] == 0) // inDegrees[i] == -1 means visited.
        dfs(graph, i, inDegrees, n, ans);

    return ans.size() == n ? ans.stream().mapToInt(Integer::intValue).toArray() : new int[] {};
  }

  private void dfs(List<Integer>[] graph, int u, int[] inDegrees, int n, List<Integer> ans) {
    if (u < n)
      ans.add(u);

    inDegrees[u] = -1; // Mark as visited.

    for (final int v : graph[u])
      if (--inDegrees[v] == 0)
        dfs(graph, v, inDegrees, n, ans);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int m, int[] group, List<List<Integer>> beforeItems, int[] output) {
        int[] expected = sortItems(n, m, group, beforeItems);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
