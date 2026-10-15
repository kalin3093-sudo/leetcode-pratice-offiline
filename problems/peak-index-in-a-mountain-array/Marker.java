import java.util.*;
class Marker {
  public int peakIndexInMountainArray(int[] arr) {
    int l = 0;
    int r = arr.length - 1;

    while (l < r) {
      final int m = (l + r) / 2;
      if (arr[m] >= arr[m + 1])
        r = m;
      else
        l = m + 1;
    }

    return l;
  }

    public boolean isCorrect(int[] arr, int output) {
        int expected = peakIndexInMountainArray(arr);
        return expected == output;
    }
}
