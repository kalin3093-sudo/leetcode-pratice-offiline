import java.util.*;
class Marker {
  public double angleClock(int hour, int minutes) {
    final double hourHand = (hour % 12 + minutes / 60.0) * 30;
    final double minuteHand = minutes * 6;
    final double diff = Math.abs(hourHand - minuteHand);
    return Math.min(diff, 360 - diff);
  }

    public boolean isCorrect(int hour, int minutes, double output) {
        double expected = angleClock(hour, minutes);
        return Math.abs(expected - output) < 1e-5;
    }
}
