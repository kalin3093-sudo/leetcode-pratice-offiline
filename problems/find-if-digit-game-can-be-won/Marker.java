import java.util.*;
class Marker {
  public boolean canAliceWin(int[] nums) {
    return Arrays.stream(nums).map(num -> num < 10 ? num : -num).sum() != 0;
  }

    public boolean isCorrect(int[] nums, boolean output) {
        boolean expected = canAliceWin(nums);
        return expected == output;
    }
}
