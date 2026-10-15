import java.util.*;
class Marker {
  public int minBitFlips(int start, int goal) {
    return Integer.bitCount(start ^ goal);
  }

    public boolean isCorrect(int start, int goal, int output) {
        int expected = minBitFlips(start, goal);
        return expected == output;
    }
}
