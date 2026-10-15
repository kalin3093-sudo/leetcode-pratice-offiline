import java.util.*;
class Marker {
  public int subtractProductAndSum(int n) {
    int prod = 1;
    int summ = 0;

    for (; n > 0; n /= 10) {
      prod *= n % 10;
      summ += n % 10;
    }

    return prod - summ;
  }

    public boolean isCorrect(int n, int output) {
        int expected = subtractProductAndSum(n);
        return expected == output;
    }
}
