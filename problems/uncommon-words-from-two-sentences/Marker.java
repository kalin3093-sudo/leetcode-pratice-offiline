import java.util.*;
class Marker {
  public String[] uncommonFromSentences(String A, String B) {
    List<String> ans = new ArrayList<>();
    Map<String, Integer> count = new HashMap<>();

    for (final String word : (A + ' ' + B).split(" "))
      count.merge(word, 1, Integer::sum);

    for (final String word : count.keySet())
      if (count.get(word) == 1)
        ans.add(word);

    return ans.toArray(new String[0]);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String s1, String s2, String[] output) {
        String[] expected = uncommonFromSentences(s1, s2);
        return Arrays.equals(sortedA(expected), sortedA(output));
    }
}
