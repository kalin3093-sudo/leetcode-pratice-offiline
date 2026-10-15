import java.util.*;
class Marker {
  public int getXORSum(int[] arr1, int[] arr2) {
    final int xors1 = Arrays.stream(arr1).reduce((a, b) -> a ^ b).getAsInt();
    final int xors2 = Arrays.stream(arr2).reduce((a, b) -> a ^ b).getAsInt();
    return xors1 & xors2;
  }

    public boolean isCorrect(int[] arr1, int[] arr2, int output) {
        int expected = getXORSum(arr1, arr2);
        return expected == output;
    }
}
