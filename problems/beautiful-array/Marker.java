import java.util.*;
class Marker {
  public int[] beautifulArray(int n) {
    int[] arr = new int[n];
    for (int i = 0; i < n; ++i)
      arr[i] = i + 1;
    divide(arr, 0, n - 1, 1);
    return arr;
  }

  private void divide(int[] arr, int l, int r, int mask) {
    if (l >= r)
      return;
    final int m = partition(arr, l, r, mask);
    divide(arr, l, m, mask << 1);
    divide(arr, m + 1, r, mask << 1);
  }

  private int partition(int[] arr, int l, int r, int mask) {
    int nextSwapped = l;
    for (int i = l; i <= r; ++i)
      if ((arr[i] & mask) > 0)
        swap(arr, i, nextSwapped++);
    return nextSwapped - 1;
  }

  private void swap(int[] arr, int i, int j) {
    final int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int[] output) {
        int[] expected = beautifulArray(n);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
