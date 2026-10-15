import java.util.*;
class Marker {
  public double averageWaitingTime(int[][] customers) {
    double wait = 0;
    double curr = 0;

    for (int[] c : customers) {
      curr = Math.max(curr, 1.0 * c[0]) + c[1];
      wait += curr - c[0];
    }

    return 1.0 * wait / customers.length;
  }

    public boolean isCorrect(int[][] customers, double output) {
        double expected = averageWaitingTime(customers);
        return Math.abs(expected - output) < 1e-5;
    }
}
