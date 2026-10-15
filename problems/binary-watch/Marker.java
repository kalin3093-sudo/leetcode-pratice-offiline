import java.util.*;
class Marker {
  public List<String> readBinaryWatch(int turnedOn) {
    List<String> ans = new ArrayList<>();
    dfs(turnedOn, 0, 0, 0, ans);
    return ans;
  }

  private int[] hours = new int[] {1, 2, 4, 8};
  private int[] minutes = new int[] {1, 2, 4, 8, 16, 32};

  private void dfs(int turnedOn, int s, int h, int m, List<String> ans) {
    if (turnedOn == 0) {
      final String time = String.valueOf(h) + ":" + (m < 10 ? "0" : "") + String.valueOf(m);
      ans.add(time);
      return;
    }

    for (int i = s; i < hours.length + minutes.length; ++i)
      if (i < 4 && h + hours[i] < 12)
        dfs(turnedOn - 1, i + 1, h + hours[i], m, ans);
      else if (i >= 4 && m + minutes[i - 4] < 60)
        dfs(turnedOn - 1, i + 1, h, m + minutes[i - 4], ans);
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int turnedOn, List<String> output) {
        List<String> expected = readBinaryWatch(turnedOn);
        return sortedL(expected).equals(sortedL(output));
    }
}
