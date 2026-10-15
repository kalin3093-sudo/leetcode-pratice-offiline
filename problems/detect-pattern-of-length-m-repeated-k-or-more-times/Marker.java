import java.util.*;
class Marker {
  public boolean containsPattern(int[] arr, int m, int k) {
    int count = 0;
    for (int i = m; i < arr.length; ++i) {
      count = arr[i] == arr[i - m] ? count + 1 : 0;
      if (count == m * k - m)
        return true;
    }
    return false;
  }

    public boolean isCorrect(int[] arr, int m, int k, boolean output) {
        boolean expected = containsPattern(arr, m, k);
        return expected == output;
    }
}
