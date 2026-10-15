import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public String findDifferentBinaryString(String[] nums) {
    final int bitSize = nums[0].length();
    final int maxNum = 1 << bitSize;
    Set<Integer> numsSet = Arrays.stream(nums)
                               .mapToInt(num -> Integer.parseInt(num, 2))
                               .boxed()
                               .collect(Collectors.toSet());

    for (int num = 0; num < maxNum; ++num)
      if (!numsSet.contains(num))
        return String.format("%" + bitSize + "s", Integer.toBinaryString(num)).replace(' ', '0');

    throw new IllegalArgumentException();
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(String[] nums, String output) {
        if (nums == null || nums.length == 0 || output == null) return false;
        int len = nums[0].length();
        if (output.length() != len) return false;
        for (int i = 0; i < output.length(); i++)
            if (output.charAt(i) != '0' && output.charAt(i) != '1') return false;
        for (String s : nums)
            if (output.equals(s)) return false;
        return true;
    }
}
