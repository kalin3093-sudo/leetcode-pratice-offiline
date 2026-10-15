import java.util.*;
class Marker {
  public long minimumTime(int[] time, int totalTrips) {
    long l = 1;
    long r = (long) Arrays.stream(time).min().getAsInt() * totalTrips;

    while (l < r) {
      final long m = (l + r) / 2;
      if (numTrips(time, m) >= totalTrips)
        r = m;
      else
        l = m + 1;
    }

    return l;
  }

  private long numTrips(int[] time, long m) {
    return Arrays.stream(time).asLongStream().reduce(0L, (subtotal, t) -> subtotal + m / t);
  }

    public boolean isCorrect(int[] time, int totalTrips, long output) {
        long expected = minimumTime(time, totalTrips);
        return expected == output;
    }
}
