import java.util.*;
class Marker {
  public int findSpecialInteger(int[] arr) {
    final int n = arr.length;
    final int quarter = n / 4;

    for (int i = 0; i < n - quarter; ++i)
      if (arr[i] == arr[i + quarter])
        return arr[i];

    throw new IllegalArgumentException();
  }

    public boolean isCorrect(int[] arr, int output) {
        int expected = findSpecialInteger(arr);
        return expected == output;
    }
}
