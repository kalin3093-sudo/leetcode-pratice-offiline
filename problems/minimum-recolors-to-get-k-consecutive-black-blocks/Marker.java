import java.util.*;
class Marker {
  public int minimumRecolors(String blocks, int k) {
    int countB = 0;
    int maxCountB = 0;

    for (int i = 0; i < blocks.length(); ++i) {
      if (blocks.charAt(i) == 'B')
        ++countB;
      if (i >= k && blocks.charAt(i - k) == 'B')
        --countB;
      maxCountB = Math.max(maxCountB, countB);
    }

    return k - maxCountB;
  }

    public boolean isCorrect(String blocks, int k, int output) {
        int expected = minimumRecolors(blocks, k);
        return expected == output;
    }
}
