import java.util.*;
class Marker {
  public int[] maxDepthAfterSplit(String seq) {
    int[] ans = new int[seq.length()];
    int depth = 1;

    // Put all odd-depth parentheses in one group and even-depth parentheses in
    // the other group.
    for (int i = 0; i < seq.length(); ++i)
      if (seq.charAt(i) == '(')
        ans[i] = ++depth % 2;
      else
        ans[i] = depth-- % 2;

    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String seq, int[] output) {
        if (seq == null || output == null || output.length != seq.length()) return false;
        int maxDepth = 0, d = 0;
        for (int i = 0; i < seq.length(); i++) {
            d += seq.charAt(i) == '(' ? 1 : -1;
            if (d > maxDepth) maxDepth = d;
        }
        int limit = (maxDepth + 1) / 2;
        int[] bal = new int[2];
        int[] depth = new int[2];
        for (int i = 0; i < seq.length(); i++) {
            int g = output[i];
            if (g != 0 && g != 1) return false;
            if (seq.charAt(i) == '(') {
                bal[g]++;
                if (bal[g] > depth[g]) depth[g] = bal[g];
            } else {
                bal[g]--;
                if (bal[g] < 0) return false;
            }
        }
        return bal[0] == 0 && bal[1] == 0 && depth[0] <= limit && depth[1] <= limit;
    }
}
