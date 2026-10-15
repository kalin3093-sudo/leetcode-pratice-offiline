import java.util.*;
class Marker {
  public int minimumBoxes(int n) {
    int nBoxes = 0;
    int nextTouchings = 0;  // j
    int currLevelBoxes = 0; // 1 + 2 + ... + j

    // Find the minimum j s.t. `nBoxes` = 1 + (1 + 2) + ... + (1 + 2 + ... + j)
    // >= n.
    while (nBoxes < n) {
      ++nextTouchings;
      currLevelBoxes += nextTouchings;
      nBoxes += currLevelBoxes;
    }

    // If nBoxes = n, the answer is `currLevelBoxes` = 1 + 2 + ... + j.
    if (nBoxes == n)
      return currLevelBoxes;

    // Otherwise, need to remove the boxes in the current level and rebuild it.
    nBoxes -= currLevelBoxes;
    currLevelBoxes -= nextTouchings;
    nextTouchings = 0;

    while (nBoxes < n) {
      ++nextTouchings;
      nBoxes += nextTouchings;
    }

    return currLevelBoxes + nextTouchings;
  }

    public boolean isCorrect(int n, int output) {
        int expected = minimumBoxes(n);
        return expected == output;
    }
}
