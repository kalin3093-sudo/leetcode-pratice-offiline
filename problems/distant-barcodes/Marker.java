import java.util.*;
class Marker {
  public int[] rearrangeBarcodes(int[] barcodes) {
    int[] ans = new int[barcodes.length];
    int[] count = new int[10001];
    int maxCount = 0;
    int maxNum = 0;

    for (final int b : barcodes)
      ++count[b];

    for (int i = 1; i < 10001; ++i)
      if (count[i] > maxCount) {
        maxCount = count[i];
        maxNum = i;
      }

    fillAns(ans, count, maxNum, barcodes.length);
    for (int num = 1; num < 10001; ++num)
      fillAns(ans, count, num, barcodes.length);

    return ans;
  }

  private int i = 0; // ans' index

  private void fillAns(int[] ans, int[] count, int num, int n) {
    while (count[num]-- > 0) {
      ans[i] = num;
      i = i + 2 < n ? i + 2 : 1;
    }
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] barcodes, int[] output) {
        int[] expected = rearrangeBarcodes(barcodes);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
