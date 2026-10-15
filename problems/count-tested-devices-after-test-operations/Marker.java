import java.util.*;
class Marker {
  public int countTestedDevices(int[] batteryPercentages) {
    int ans = 0;

    for (final int batteryPercentage : batteryPercentages)
      if (batteryPercentage - ans > 0)
        ++ans;

    return ans;
  }

    public boolean isCorrect(int[] batteryPercentages, int output) {
        int expected = countTestedDevices(batteryPercentages);
        return expected == output;
    }
}
