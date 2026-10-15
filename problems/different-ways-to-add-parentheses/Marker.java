import java.util.*;
class Marker {
  public List<Integer> diffWaysToCompute(String expression) {
    return ways(expression, new HashMap<>());
  }

  private List<Integer> ways(final String s, Map<String, List<Integer>> mem) {
    if (mem.containsKey(s))
      return mem.get(s);

    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < s.length(); ++i)
      if (!Character.isDigit(s.charAt(i)))
        for (final int a : ways(s.substring(0, i), mem))
          for (final int b : ways(s.substring(i + 1), mem))
            if (s.charAt(i) == '+')
              ans.add(a + b);
            else if (s.charAt(i) == '-')
              ans.add(a - b);
            else
              ans.add(a * b);

    if (ans.isEmpty()) { // Single number
      mem.put(s, Arrays.asList(Integer.parseInt(s)));
      return mem.get(s);
    }
    mem.put(s, ans);
    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String expression, List<Integer> output) {
        List<Integer> expected = diffWaysToCompute(expression);
        return sortedL(expected).equals(sortedL(output));
    }
}
