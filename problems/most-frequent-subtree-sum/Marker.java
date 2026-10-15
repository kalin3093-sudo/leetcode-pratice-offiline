import java.util.*;
class Marker {
  public int[] findFrequentTreeSum(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();
    int maxCount = 0;

    sumDownFrom(root, count);

    for (final int freq : count.values())
      maxCount = Math.max(maxCount, freq);

    for (final int sum : count.keySet())
      if (count.get(sum) == maxCount)
        ans.add(sum);

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

  private int sumDownFrom(TreeNode root, Map<Integer, Integer> count) {
    if (root == null)
      return 0;

    final int sum = root.val + sumDownFrom(root.left, count) + sumDownFrom(root.right, count);
    count.merge(sum, 1, Integer::sum);
    return sum;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(TreeNode root, int[] output) {
        int[] expected = findFrequentTreeSum(root);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
