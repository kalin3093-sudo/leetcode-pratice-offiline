import java.util.*;
class Marker {
  public int minDominoRotations(int[] tops, int[] bottoms) {
    final int n = tops.length;
    int[] countTops = new int[7];
    int[] countBottoms = new int[7];
    int[] countBoth = new int[7];

    for (int i = 0; i < n; ++i) {
      ++countTops[tops[i]];
      ++countBottoms[bottoms[i]];
      if (tops[i] == bottoms[i])
        ++countBoth[tops[i]];
    }

    for (int i = 1; i <= 6; ++i)
      if (countTops[i] + countBottoms[i] - countBoth[i] == n)
        return n - Math.max(countTops[i], countBottoms[i]);

    return -1;
  }

    public boolean isCorrect(int[] tops, int[] bottoms, int output) {
        int expected = minDominoRotations(tops, bottoms);
        return expected == output;
    }
}
