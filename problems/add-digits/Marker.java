import java.util.*;
class Marker {
  public int addDigits(int num) {
    return 1 + (num - 1) % 9;
  }

    public boolean isCorrect(int num, int output) {
        int expected = addDigits(num);
        return expected == output;
    }
}
