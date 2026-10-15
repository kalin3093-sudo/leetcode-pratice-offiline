import java.util.*;
class Marker {
  public int countDigits(int num) {
    int ans = 0;

    for (int n = num; n > 0; n /= 10)
      if (num % (n % 10) == 0)
        ++ans;

    return ans;
  }

    public boolean isCorrect(int num, int output) {
        int expected = countDigits(num);
        return expected == output;
    }
}
