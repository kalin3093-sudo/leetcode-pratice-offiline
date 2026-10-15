import java.util.*;
class Marker {
  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);
    arr[0] = 1;

    for (int i = 1; i < arr.length; ++i)
      arr[i] = Math.min(arr[i], arr[i - 1] + 1);

    return arr[arr.length - 1];
  }

    public boolean isCorrect(int[] arr, int output) {
        int expected = maximumElementAfterDecrementingAndRearranging(arr);
        return expected == output;
    }
}
