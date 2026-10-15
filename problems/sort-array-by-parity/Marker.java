import java.util.*;
class Marker {
  public int[] sortArrayByParity(int[] nums) {
    int l = 0;
    int r = nums.length - 1;

    while (l < r) {
      if (nums[l] % 2 == 1 && nums[r] % 2 == 0) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
      }
      if (nums[l] % 2 == 0)
        ++l;
      if (nums[r] % 2 == 1)
        --r;
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
        int[] expected = sortArrayByParity(nums);
        return Arrays.equals(sortedI(expected), sortedI(output));
    }
}
