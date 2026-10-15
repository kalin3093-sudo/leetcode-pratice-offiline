import java.util.*;
class Marker {
  public double nthPersonGetsNthSeat(int n) {
    return n == 1 ? 1 : 0.5;
  }

    public boolean isCorrect(int n, double output) {
        double expected = nthPersonGetsNthSeat(n);
        return Math.abs(expected - output) < 1e-5;
    }
}
