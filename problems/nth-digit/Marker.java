import java.util.*;
class Marker {
  public int findNthDigit(int n) {
    int digitSize = 1;
    int startNum = 1;
    long count = 9;

    while (digitSize * count < n) {
      n -= digitSize * count;
      ++digitSize;
      startNum *= 10;
      count *= 10;
    }

    final int targetNum = startNum + (n - 1) / digitSize;
    final int index = (n - 1) % digitSize;
    return String.valueOf(targetNum).charAt(index) - '0';
  }

    public boolean isCorrect(int n, int output) {
        int expected = findNthDigit(n);
        return expected == output;
    }
}
