import java.util.*;
class Marker {
  public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
    return targetCapacity == 0 || jug1Capacity + jug2Capacity >= targetCapacity &&
                                      targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0;
  }

  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

    public boolean isCorrect(int x, int y, int target, boolean output) {
        boolean expected = canMeasureWater(x, y, target);
        return expected == output;
    }
}
