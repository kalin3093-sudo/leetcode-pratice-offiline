import java.util.*;
class Marker {
  public int countNumbersWithUniqueDigits(int n) {
    if (n == 0)
      return 1;

    int ans = 10;
    int uniqueDigits = 9;

    for (int availableNum = 9; n > 1 && availableNum > 0; --n, --availableNum) {
      uniqueDigits *= availableNum;
      ans += uniqueDigits;
    }

    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = countNumbersWithUniqueDigits(n);
        return expected == output;
    }
}
