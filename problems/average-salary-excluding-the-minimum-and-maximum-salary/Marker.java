import java.util.*;
class Marker {
  public double average(int[] salary) {
    final double sum = Arrays.stream(salary).sum();
    final int mx = Arrays.stream(salary).max().getAsInt();
    final int mn = Arrays.stream(salary).min().getAsInt();
    return (sum - mx - mn) / (salary.length - 2);
  }

    public boolean isCorrect(int[] salary, double output) {
        double expected = average(salary);
        return Math.abs(expected - output) < 1e-5;
    }
}
