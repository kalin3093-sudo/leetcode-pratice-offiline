import java.util.*;
class Marker {
  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length)
      return intersect(nums2, nums1);

    List<Integer> ans = new ArrayList<>();
    Map<Integer, Integer> count = new HashMap<>();

    for (final int num : nums1)
      count.put(num, count.getOrDefault(num, 0) + 1);

    for (final int num : nums2)
      if (count.containsKey(num) && count.get(num) > 0) {
        ans.add(num);
        count.put(num, count.get(num) - 1);
      }

    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] nums1, int[] nums2, int[] output) {
        int[] expected = intersect(nums1, nums2);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
