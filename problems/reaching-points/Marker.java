import java.util.*;
class Marker {
  public boolean reachingPoints(int sx, int sy, int tx, int ty) {
    while (sx < tx && sy < ty)
      if (tx > ty)
        tx %= ty;
      else
        ty %= tx;

    return                                             //
        sx == tx && sy <= ty && (ty - sy) % tx == 0 || //
        sy == ty && sx <= tx && (tx - sx) % ty == 0;
  }

    public boolean isCorrect(int sx, int sy, int tx, int ty, boolean output) {
        boolean expected = reachingPoints(sx, sy, tx, ty);
        return expected == output;
    }
}
