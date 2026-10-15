import java.util.*;
class Marker {
  public int minMoves(int target, int maxDoubles) {
    int steps = 0;

    while (target > 1 && maxDoubles > 0) {
      if (target % 2 == 1) {
        --target;
      } else {
        target /= 2;
        --maxDoubles;
      }
      ++steps;
    }

    return steps + target - 1;
  }

    public boolean isCorrect(int target, int maxDoubles, int output) {
        int expected = minMoves(target, maxDoubles);
        return expected == output;
    }
}
