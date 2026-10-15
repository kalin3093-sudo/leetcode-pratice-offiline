import java.util.*;
class Marker {
  public List<Integer> pancakeSort(int[] arr) {
    List<Integer> ans = new ArrayList<>();

    for (int target = arr.length; target >= 1; --target) {
      int index = find(arr, target);
      reverse(arr, 0, index);
      reverse(arr, 0, target - 1);
      ans.add(index + 1);
      ans.add(target);
    }

    return ans;
  }

  private int find(int[] arr, int target) {
    for (int i = 0; i < arr.length; ++i)
      if (arr[i] == target)
        return i;
    throw new IllegalArgumentException();
  }

  private void reverse(int[] arr, int l, int r) {
    while (l < r)
      swap(arr, l++, r--);
  }

  private void swap(int[] arr, int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }

      static int[] sortedI(int[] a){int[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static int[][] sortedI2(int[][] a){int[][] b=a.clone();java.util.Arrays.sort(b,(x,y)->java.util.Arrays.compare(x,y));return b;}
  static String[] sortedA(String[] a){String[] b=a.clone();java.util.Arrays.sort(b);return b;}
  static <T extends Comparable<? super T>> java.util.List<T> sortedL(java.util.List<T> l){java.util.List<T> c=new java.util.ArrayList<>(l);java.util.Collections.sort(c);return c;}
  static java.util.List<String> sortedLL(java.util.List<?> l){java.util.List<String> c=new java.util.ArrayList<>();for(Object o:l)c.add(String.valueOf(o));java.util.Collections.sort(c);return c;}
  static String sortedS(String s){char[] c=s.toCharArray();java.util.Arrays.sort(c);return new String(c);}
  
  public boolean isCorrect(int[] arr, List<Integer> output) {
        if (arr == null || output == null) return false;
        if (output.size() > 10 * arr.length) return false;
        int[] a = arr.clone();
        for (Integer kObj : output) {
            if (kObj == null) return false;
            int k = kObj;
            if (k < 1 || k > a.length) return false;
            for (int l = 0, r = k - 1; l < r; l++, r--) {
                int t = a[l];
                a[l] = a[r];
                a[r] = t;
            }
        }
        for (int i = 1; i < a.length; i++)
            if (a[i - 1] > a[i]) return false;
        return true;
    }
}
