import java.util.*;
class Marker {
  public boolean checkPowersOfThree(int n) {
    while (n > 1) {
      final int r = n % 3;
      if (r == 2)
        return false;
      n /= 3;
    }

    return true;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = checkPowersOfThree(n);
        return expected == output;
    }
}
