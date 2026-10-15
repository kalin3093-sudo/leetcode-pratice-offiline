import java.util.*;
class Marker {
  public boolean threeConsecutiveOdds(int[] arr) {
    int count = 0;
    for (final int a : arr) {
      count = a % 2 == 0 ? 0 : count + 1;
      if (count == 3)
        return true;
    }
    return false;
  }

    public boolean isCorrect(int[] arr, boolean output) {
        boolean expected = threeConsecutiveOdds(arr);
        return expected == output;
    }
}
