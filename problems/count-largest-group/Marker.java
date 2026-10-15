import java.util.*;
class Marker {
  public int countLargestGroup(int n) {
    int[] count = new int[9 * 4 + 1];
    for (int i = 1; i <= n; ++i)
      ++count[getDigitSum(i)];
    final int mx = Arrays.stream(count).max().getAsInt();
    return (int) Arrays.stream(count).filter(c -> c == mx).count();
  }

  private int getDigitSum(int num) {
    int digitSum = 0;
    while (num > 0) {
      digitSum += num % 10;
      num /= 10;
    }
    return digitSum;
  }

    public boolean isCorrect(int n, int output) {
        int expected = countLargestGroup(n);
        return expected == output;
    }
}
