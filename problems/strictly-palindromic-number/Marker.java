import java.util.*;
class Marker {
  public boolean isStrictlyPalindromic(int n) {
    return false;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = isStrictlyPalindromic(n);
        return expected == output;
    }
}
