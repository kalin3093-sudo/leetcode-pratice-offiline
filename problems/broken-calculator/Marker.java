import java.util.*;
class Marker {
  public int brokenCalc(int startValue, int target) {
    int ops = 0;

    while (startValue < target) {
      if (target % 2 == 0)
        target /= 2;
      else
        ++target;
      ++ops;
    }

    return ops + startValue - target;
  }

    public boolean isCorrect(int startValue, int target, int output) {
        int expected = brokenCalc(startValue, target);
        return expected == output;
    }
}
