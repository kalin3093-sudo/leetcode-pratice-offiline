import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
    final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    List<int[]> ans = new ArrayList<>();
    boolean[][] seen = new boolean[rows][cols];
    Queue<Pair<Integer, Integer>> q = new LinkedList<>(Arrays.asList(new Pair<>(rCenter, cCenter)));
    seen[rCenter][cCenter] = true;

    while (!q.isEmpty()) {
      final int i = q.peek().getKey();
      final int j = q.poll().getValue();
      ans.add(new int[] {i, j});
      for (int[] dir : DIRS) {
        final int x = i + dir[0];
        final int y = j + dir[1];
        if (x < 0 || x == rows || y < 0 || y == cols)
          continue;
        if (seen[x][y])
          continue;
        seen[x][y] = true;
        q.offer(new Pair<>(x, y));
      }
    }

    return ans.toArray(int[][] ::new);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int rows, int cols, int rCenter, int cCenter, int[][] output) {
        int[][] expected = allCellsDistOrder(rows, cols, rCenter, cCenter);
        return Arrays.deepEquals(sortedI2(expected), sortedI2(output));
    }
}
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
