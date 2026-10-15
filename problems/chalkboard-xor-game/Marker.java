import java.util.*;
class Marker {
  public boolean xorGame(int[] nums) {
    final int xors = Arrays.stream(nums).reduce((a, b) -> a ^ b).getAsInt();
    return xors == 0 || nums.length % 2 == 0;
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = xorGame(nums);
        return expected == output;
    }
}
