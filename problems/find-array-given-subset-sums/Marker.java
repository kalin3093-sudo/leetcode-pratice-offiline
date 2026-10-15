import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int[] recoverArray(int n, int[] sums) {
    Arrays.sort(sums);
    return recover(sums).stream().mapToInt(Integer::intValue).toArray();
  }

  private List<Integer> recover(int[] sums) {
    if (sums.length == 1) // sums[0] must be 0.
      return new ArrayList<>();

    Map<Integer, Long> count = Arrays.stream(sums).boxed().collect(
        Collectors.groupingBy(Function.identity(), Collectors.counting()));

    // Either num or -num must be in the final array.
    //  num + sumsExcludingNum = sumsIncludingNum
    // -num + sumsIncludingNum = sumsExcludingNum
    final int num = sums[1] - sums[0];
    int i = 0; // sumsExcludingNum/sumsIncludingNum's index
    int[] sumsExcludingNum = new int[sums.length / 2];
    int[] sumsIncludingNum = new int[sums.length / 2];
    boolean chooseSumsIncludingNum = false;

    for (final int sum : sums) {
      if (count.get(sum) == 0)
        continue;
      count.merge(sum, -1L, Long::sum);
      count.merge(sum + num, -1L, Long::sum);
      sumsExcludingNum[i] = sum;
      sumsIncludingNum[i] = sum + num;
      ++i;
      if (sum + num == 0)
        chooseSumsIncludingNum = true;
    }

    // Choose `sumsExludingNum` by default since we want to gradually strip
    // `num` from each sum in `sums` to have the final array. However, we should
    // always choose the group of sums with 0 since it's a must-have.
    List<Integer> recovered = recover(chooseSumsIncludingNum ? sumsIncludingNum : sumsExcludingNum);
    recovered.add(chooseSumsIncludingNum ? -num : num);
    return recovered;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int n, int[] sums, int[] output) {
        if (output == null || output.length != n) return false;
        int total = 1 << n;
        if (sums == null || sums.length != total) return false;
        long[] got = new long[total];
        for (int mask = 0; mask < total; mask++) {
            long s = 0;
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0) s += output[i];
            got[mask] = s;
        }
        long[] want = new long[total];
        for (int i = 0; i < total; i++) want[i] = sums[i];
        Arrays.sort(got);
        Arrays.sort(want);
        return Arrays.equals(got, want);
    }
}
