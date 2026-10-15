import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.Arrays;

class Marker {
  public int findLongestChain(int[][] pairs) {
    int ans = 0;
    int prevEnd = Integer.MIN_VALUE;

    Arrays.sort(pairs, Comparator.comparingInt(pair -> pair[1]));

    for (int[] pair : pairs)
      if (pair[0] > prevEnd) {
        ++ans;
        prevEnd = pair[1];
      }

    return ans;
  }

    public boolean isCorrect(int[][] pairs, int output) {
        int expected = findLongestChain(pairs);
        return expected == output;
    }
}
