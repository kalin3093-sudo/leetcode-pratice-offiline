import java.util.*;
class Marker {
  public char kthCharacter(int k) {
    return (char) ('a' + Integer.bitCount(k - 1));
  }

    public boolean isCorrect(int k, char output) {
        char expected = kthCharacter(k);
        return expected == output;
    }
}
