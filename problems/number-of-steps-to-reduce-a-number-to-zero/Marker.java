import java.util.*;
class Marker {
  public int numberOfSteps(int num) {
    if (num == 0)
      return 0;
    final int subtractSteps = Integer.bitCount(num);
    final int divideSteps = 31 - Integer.numberOfLeadingZeros(num);
    return subtractSteps + divideSteps;
  }

    public boolean isCorrect(int num, int output) {
        int expected = numberOfSteps(num);
        return expected == output;
    }
}
