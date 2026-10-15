import java.util.*;
class Marker {
  public int[] rearrangeArray(int[] nums) {
    Arrays.sort(nums);
    for (int i = 1; i < nums.length; i += 2) {
      final int temp = nums[i];
      nums[i] = nums[i - 1];
      nums[i - 1] = temp;
    }
    return nums;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] nums, int[] output) {
        int[] expected = rearrangeArray(nums);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
