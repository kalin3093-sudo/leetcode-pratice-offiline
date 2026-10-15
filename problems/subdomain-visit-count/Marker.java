import java.util.*;
class Marker {
  public List<String> subdomainVisits(String[] cpdomains) {
    List<String> ans = new ArrayList<>();
    Map<String, Integer> count = new HashMap<>();

    for (final String cpdomain : cpdomains) {
      final int space = cpdomain.indexOf(' ');
      final int num = Integer.valueOf(cpdomain.substring(0, space));
      final String domain = cpdomain.substring(space + 1);
      count.merge(domain, num, Integer::sum);
      for (int i = 0; i < domain.length(); ++i)
        if (domain.charAt(i) == '.') {
          String subdomain = domain.substring(i + 1);
          count.merge(subdomain, num, Integer::sum);
        }
    }

    for (final String subdomain : count.keySet())
      ans.add(String.valueOf(count.get(subdomain)) + ' ' + subdomain);

    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String[] cpdomains, List<String> output) {
        List<String> expected = subdomainVisits(cpdomains);
        return sortedL(expected).equals(sortedL(output));
    }
}
