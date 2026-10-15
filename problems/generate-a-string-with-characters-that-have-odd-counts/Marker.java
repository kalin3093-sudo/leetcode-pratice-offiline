import java.util.*;
class Marker {
  public String generateTheString(int n) {
    StringBuilder sb = new StringBuilder(n);
    for (int i = 0; i < n; ++i)
      sb.append('a');
    if (n % 2 == 0)
      sb.setCharAt(n - 1, 'b');
    return sb.toString();
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, String output) {
        if (output == null || output.length() != n) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            char c = output.charAt(i);
            if (c < 'a' || c > 'z') return false;
            cnt[c - 'a']++;
        }
        for (int v : cnt)
            if (v != 0 && v % 2 == 0) return false;
        return true;
    }
}
