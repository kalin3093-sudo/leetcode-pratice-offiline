import java.util.*;
class Marker {
  public boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
    final int minStep = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));
    return minStep == 0 ? t != 1 : minStep <= t;
  }

    public boolean isCorrect(int sx, int sy, int fx, int fy, int t, boolean output) {
        boolean expected = isReachableAtTime(sx, sy, fx, fy, t);
        return expected == output;
    }
}
