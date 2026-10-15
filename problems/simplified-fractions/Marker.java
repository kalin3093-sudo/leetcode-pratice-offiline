import java.util.*;
class Marker {
  public List<String> simplifiedFractions(int n) {
    List<String> ans = new ArrayList<>();
    for (int denominator = 2; denominator <= n; ++denominator)
      for (int numerator = 1; numerator < denominator; ++numerator)
        if (gcd(denominator, numerator) == 1)
          ans.add(String.valueOf(numerator) + "/" + String.valueOf(denominator));
    return ans;
  }

  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, List<String> output) {
        List<String> expected = simplifiedFractions(n);
        return sortedL(expected).equals(sortedL(output));
    }
}
