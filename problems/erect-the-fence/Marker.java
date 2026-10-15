import java.util.*;
// Monotone Chain
class Marker {
  public int[][] outerTrees(int[][] trees) {
    Stack<int[]> hull = new Stack<>();

    Arrays.sort(
        trees,
        Comparator.comparingInt((int[] tree) -> tree[0]).thenComparingInt((int[] tree) -> tree[1]));

    // Build the lower hull: left-to-right scan.
    for (int[] tree : trees) {
      while (hull.size() > 1 && cross(hull.peek(), hull.get(hull.size() - 2), tree) > 0)
        hull.pop();
      hull.push(tree);
    }
    hull.pop();

    // Build the upper hull: right-to-left scan.
    for (int i = trees.length - 1; i >= 0; --i) {
      while (hull.size() > 1 && cross(hull.peek(), hull.get(hull.size() - 2), trees[i]) > 0)
        hull.pop();
      hull.push(trees[i]);
    }

    // Remove the redundant elements from the stack.
    return new HashSet<>(hull).stream().toArray(int[][] ::new);
  }

  private int cross(int[] p, int[] q, int[] r) {
    return (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1]);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[][] trees, int[][] output) {
        int[][] expected = outerTrees(trees);
        return Arrays.deepEquals(sortedI2(expected), sortedI2(output));
    }
}
