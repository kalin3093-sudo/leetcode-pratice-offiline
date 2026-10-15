import java.util.*;
class Marker {
  public List<String> ambiguousCoordinates(String s) {
    List<String> ans = new ArrayList<>();
    s = s.substring(1, s.length() - 1);

    for (int i = 1; i < s.length(); ++i)
      for (final String x : splits(s.substring(0, i)))
        for (final String y : splits(s.substring(i)))
          ans.add("(" + x + ", " + y + ")");

    return ans;
  }

  private List<String> splits(final String s) {
    if (s.isEmpty() || s.length() > 1 && s.charAt(0) == '0' && s.charAt(s.length() - 1) == '0')
      return new ArrayList<>();
    if (s.charAt(s.length() - 1) == '0')
      return List.of(s);
    if (s.charAt(0) == '0')
      return List.of("0." + s.substring(1));

    List<String> res = new ArrayList<>(List.of(s));
    for (int i = 1; i < s.length(); ++i)
      res.add(s.substring(0, i) + "." + s.substring(i));
    return res;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String s, List<String> output) {
        List<String> expected = ambiguousCoordinates(s);
        return sortedL(expected).equals(sortedL(output));
    }
}
