import java.util.*;
class Marker {
  public int countEven(int num) {
    if (getDigitSum(num) % 2 == 0)
      return num / 2;
    return (num - 1) / 2;
  }

  private int getDigitSum(int num) {
    int digitSum = 0;
    while (num > 0) {
      digitSum += num % 10;
      num /= 10;
    }
    return digitSum;
  }

    public boolean isCorrect(int num, int output) {
        int expected = countEven(num);
        return expected == output;
    }
}
