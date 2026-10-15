import java.util.*;
class Marker {
  public long coloredCells(int n) {
    return 1L * n * n + 1L * (n - 1) * (n - 1);
  }

    public boolean isCorrect(int n, long output) {
        long expected = coloredCells(n);
        return expected == output;
    }
}
