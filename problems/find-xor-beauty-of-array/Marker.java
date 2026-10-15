import java.util.*;
class Marker {
  public int xorBeauty(int[] nums) {
    return Arrays.stream(nums).reduce((a, b) -> a ^ b).getAsInt();
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = xorBeauty(nums);
        return expected == output;
    }
}
