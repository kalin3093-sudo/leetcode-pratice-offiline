import java.util.*;
class Marker {
  public int sum(int num1, int num2) {
    return num1 + num2;
  }

    public boolean isCorrect(int num1, int num2, int output) {
        int expected = sum(num1, num2);
        return expected == output;
    }
}
