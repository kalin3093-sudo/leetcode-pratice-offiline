import java.util.*;
class Marker {
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    int[] ans = new int[arr1.length];
    int[] count = new int[1001];
    int i = 0;

    for (int a : arr1)
      ++count[a];

    for (int a : arr2)
      while (count[a]-- > 0)
        ans[i++] = a;

    for (int num = 0; num < 1001; ++num)
      while (count[num]-- > 0)
        ans[i++] = num;

    return ans;
  }

    public boolean isCorrect(int[] arr1, int[] arr2, int[] output) {
        int[] expected = relativeSortArray(arr1, arr2);
        return Arrays.equals(expected, output);
    }
}
