import java.util.*;
class Marker {
  public boolean isPalindrome(int x) {
    if (x < 0)
      return false;

    long reversed = 0;
    int y = x;

    while (y > 0) {
      reversed = reversed * 10 + y % 10;
      y /= 10;
    }

    return reversed == x;
  }

    public boolean isCorrect(int x, boolean output) {
        boolean expected = isPalindrome(x);
        return expected == output;
    }
}
