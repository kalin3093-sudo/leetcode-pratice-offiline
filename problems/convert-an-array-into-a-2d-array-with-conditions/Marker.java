import java.util.*;
class Marker {
  public List<List<Integer>> findMatrix(int[] nums) {
    // The number of rows we need equals the maximum frequency.
    List<List<Integer>> ans = new ArrayList<>();
    int[] count = new int[nums.length + 1];

    for (final int num : nums) {
      // Construct `ans` on demand.
      if (++count[num] > ans.size())
        ans.add(new ArrayList<>());
      ans.get(count[num] - 1).add(num);
    }

    return ans;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] nums, List<List<Integer>> output) {
        if (output == null) return false;
        Map<Integer, Integer> need = new HashMap<>();
        for (int x : nums) need.merge(x, 1, Integer::sum);
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> firstRow = new HashMap<>();
        Map<Integer, Integer> lastRow = new HashMap<>();
        for (int i = 0; i < output.size(); i++) {
            List<Integer> row = output.get(i);
            if (row == null) return false;
            Set<Integer> inRow = new HashSet<>();
            for (int x : row) {
                // each row must contain distinct integers
                if (!inRow.add(x)) return false;
                count.merge(x, 1, Integer::sum);
                firstRow.putIfAbsent(x, i);
                lastRow.put(x, i);
            }
        }
        // rows must cover exactly the multiset of nums
        if (!count.equals(need)) return false;
        // rows containing a value must be contiguous (rows are distinct per value)
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int v = e.getKey();
            if (lastRow.get(v) - firstRow.get(v) + 1 != e.getValue()) return false;
        }
        return true;
    }
}
