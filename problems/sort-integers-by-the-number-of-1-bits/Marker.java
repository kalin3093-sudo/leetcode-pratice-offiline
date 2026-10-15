import java.util.*;
class Marker {
  public int[] sortByBits(int[] arr) {
    return Arrays.stream(arr)
        .boxed()
        .sorted(Comparator.comparingInt(Integer::bitCount).thenComparingInt(a -> a))
        .mapToInt(Integer::intValue)
        .toArray();
  }

    public boolean isCorrect(int[] arr, int[] output) {
        int[] expected = sortByBits(arr);
        return Arrays.equals(expected, output);
    }
}
