import java.util.*;
class Marker {
  public int minCostToMoveChips(int[] position) {
    int[] count = new int[2];
    for (final int p : position)
      ++count[p % 2];
    return Math.min(count[0], count[1]);
  }

    public boolean isCorrect(int[] position, int output) {
        int expected = minCostToMoveChips(position);
        return expected == output;
    }
}
