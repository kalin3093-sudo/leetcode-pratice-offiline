import java.util.*;
class Marker {
  public double trimMean(int[] arr) {
    Arrays.sort(arr);
    final int offset = arr.length / 20;
    return Arrays.stream(Arrays.copyOfRange(arr, offset, arr.length - offset)).average().orElse(0);
  }

    public boolean isCorrect(int[] arr, double output) {
        double expected = trimMean(arr);
        return Math.abs(expected - output) < 1e-5;
    }
}
