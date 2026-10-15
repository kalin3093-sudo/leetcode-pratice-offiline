import java.util.*;
class Marker {
  public int minOperations(int[] nums, int k) {
    final int xors = Arrays.stream(nums).reduce((a, b) -> a ^ b).getAsInt();
    return Integer.bitCount(k ^ xors);
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = minOperations(nums, k);
        return expected == output;
    }
}
