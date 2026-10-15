import java.util.*;
class Marker {
  public int distanceBetweenBusStops(int[] distance, int start, int destination) {
    int clockwise = 0;
    int counterclockwise = 0;

    if (start > destination) {
      int temp = start;
      start = destination;
      destination = temp;
    }

    for (int i = 0; i < distance.length; ++i) {
      if (i >= start && i < destination)
        clockwise += distance[i];
      else
        counterclockwise += distance[i];
    }

    return Math.min(clockwise, counterclockwise);
  }

    public boolean isCorrect(int[] distance, int start, int destination, int output) {
        int expected = distanceBetweenBusStops(distance, start, destination);
        return expected == output;
    }
}
