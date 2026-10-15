import java.util.*;
class Marker {
  public boolean asteroidsDestroyed(int mass, int[] asteroids) {
    Arrays.sort(asteroids);

    long m = mass;

    for (final int asteroid : asteroids)
      if (m >= asteroid)
        m += asteroid;
      else
        return false;

    return true;
  }

    public boolean isCorrect(int mass, int[] asteroids, boolean output) {
        boolean expected = asteroidsDestroyed(mass, asteroids);
        return expected == output;
    }
}
