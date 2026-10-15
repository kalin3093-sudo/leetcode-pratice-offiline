import java.util.*;

class Marker {
  public boolean sumOfNumberAndReverse(int num) {
    for (int i = 0; i <= num; i++) {
      if (i + reverse(i) == num) return true;
    }
    return false;
  }

  public boolean isCorrect(int num, boolean output) {
    return sumOfNumberAndReverse(num) == output;
  }

  private int reverse(int x) {
    int r = 0;
    while (x > 0) {
      r = r * 10 + x % 10;
      x /= 10;
    }
    return r;
  }
}
