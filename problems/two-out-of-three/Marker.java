import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
    List<Integer> ans = new ArrayList<>();
    int[] count = new int[101];

    for (int[] nums : new int[][] {nums1, nums2, nums3})
      update(count, nums);

    for (int i = 1; i <= 100; ++i)
      if (count[i] >= 2)
        ans.add(i);

    return ans;
  }

  private void update(int[] count, int[] nums) {
    for (final int num : Arrays.stream(nums).boxed().collect(Collectors.toSet()))
      ++count[num];
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] nums1, int[] nums2, int[] nums3, List<Integer> output) {
        List<Integer> expected = twoOutOfThree(nums1, nums2, nums3);
        return sortedL(expected).equals(sortedL(output));
    }
}
