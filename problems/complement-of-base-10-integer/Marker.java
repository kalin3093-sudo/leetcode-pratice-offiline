import java.util.*;
class Marker {
  public int bitwiseComplement(int n) {
    int mask = 1;
    while (mask < n)
      mask = (mask << 1) + 1;
    return mask ^ n;
  }

    public boolean isCorrect(int n, int output) {
        int expected = bitwiseComplement(n);
        return expected == output;
    }
}
