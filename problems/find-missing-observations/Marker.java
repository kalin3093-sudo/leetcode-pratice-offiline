import java.util.*;
class Marker {
  public int[] missingRolls(int[] rolls, int mean, int n) {
    final int targetSum = (rolls.length + n) * mean;
    int missingSum = targetSum - Arrays.stream(rolls).sum();
    if (missingSum > n * 6 || missingSum < n)
      return new int[] {};

    int[] ans = new int[n];
    Arrays.fill(ans, missingSum / n);
    missingSum %= n;
    for (int i = 0; i < missingSum; ++i)
      ++ans[i];

    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] rolls, int mean, int n, int[] output) {
        int[] expected = missingRolls(rolls, mean, n);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
