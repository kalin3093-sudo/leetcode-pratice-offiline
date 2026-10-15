import java.util.*;
class Marker {
  public String crackSafe(int n, int k) {
    final String allZeros = "0".repeat(n);
    StringBuilder sb = new StringBuilder(allZeros);
    dfs((int) Math.pow(k, n), n, k, new HashSet<>(Arrays.asList(allZeros)), sb);
    return sb.toString();
  }

  private boolean dfs(int passwordSize, int n, int k, Set<String> seen, StringBuilder path) {
    if (seen.size() == passwordSize)
      return true;

    StringBuilder prefix = new StringBuilder(path.substring(path.length() - n + 1));

    for (char c = '0'; c < '0' + k; ++c) {
      prefix.append(c);
      final String prefixStr = prefix.toString();
      if (!seen.contains(prefixStr)) {
        seen.add(prefixStr);
        path.append(c);
        if (dfs(passwordSize, n, k, seen, path))
          return true;
        path.deleteCharAt(path.length() - 1);
        seen.remove(prefixStr);
      }
      prefix.deleteCharAt(prefix.length() - 1);
    }

    return false;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int k, String output) {
        String expected = crackSafe(n, k);
        return sortedS(expected).equals(sortedS(output));
    }
}
