import java.util.*;
class Marker {
  public int theMaximumAchievableX(int num, int t) {
    return num + 2 * t;
  }

    public boolean isCorrect(int num, int t, int output) {
        int expected = theMaximumAchievableX(num, t);
        return expected == output;
    }
}
