import java.util.*;
class Marker {
  public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
    return (arrivalTime + delayedTime) % 24;
  }

    public boolean isCorrect(int arrivalTime, int delayedTime, int output) {
        int expected = findDelayedArrivalTime(arrivalTime, delayedTime);
        return expected == output;
    }
}
