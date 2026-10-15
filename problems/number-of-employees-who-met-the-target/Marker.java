import java.util.*;
class Marker {
  public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
    return (int) Arrays.stream(hours).filter(hour -> hour >= target).count();
  }

    public boolean isCorrect(int[] hours, int target, int output) {
        int expected = numberOfEmployeesWhoMetTarget(hours, target);
        return expected == output;
    }
}
