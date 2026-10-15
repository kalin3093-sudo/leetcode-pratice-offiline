import java.util.*;
class Marker {
  public int smallestNumber(int n) {
    return (1 << bitLength(n)) - 1;
  }

  private int bitLength(int n) {
    return Integer.SIZE - Integer.numberOfLeadingZeros(n);
  }

    public boolean isCorrect(int n, int output) {
        int expected = smallestNumber(n);
        return expected == output;
    }
}
