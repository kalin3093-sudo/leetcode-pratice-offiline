import java.util.*;
class Marker {
  public double calculateTax(int[][] brackets, int income) {
    double ans = 0;
    int prev = 0;

    for (int[] b : brackets) {
      final int upper = b[0];
      final int percent = b[1];
      if (income < upper)
        return ans + (income - prev) * percent / 100.0;
      ans += (upper - prev) * percent / 100.0;
      prev = upper;
    }

    return ans;
  }

    public boolean isCorrect(int[][] brackets, int income, double output) {
        double expected = calculateTax(brackets, income);
        return Math.abs(expected - output) < 1e-5;
    }
}
