import java.util.*;
class Marker {
  public long minCost(int[] arr, int[] brr, long k) {
    return Math.min(noSortCost(arr, brr), sortCost(arr, brr) + k);
  }

  private long noSortCost(int[] arr, int[] brr) {
    long res = 0;
    for (int i = 0; i < arr.length; ++i)
      res += Math.abs(arr[i] - brr[i]);
    return res;
  }

  private long sortCost(int[] arr, int[] brr) {
    Arrays.sort(arr);
    Arrays.sort(brr);
    long res = 0;
    for (int i = 0; i < arr.length; ++i)
      res += Math.abs(arr[i] - brr[i]);
    return res;
  }

    public boolean isCorrect(int[] arr, int[] brr, long k, long output) {
        long expected = minCost(arr, brr, k);
        return expected == output;
    }
}
