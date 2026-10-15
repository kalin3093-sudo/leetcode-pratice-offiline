import java.util.*;
class Marker {
  public int[] closestDivisors(int num) {
    for (int root = (int) Math.sqrt(num + 2); root > 0; --root)
      for (int cand : new int[] {num + 1, num + 2})
        if (cand % root == 0)
          return new int[] {root, cand / root};

    throw new IllegalArgumentException();
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int num, int[] output) {
        int[] expected = closestDivisors(num);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
