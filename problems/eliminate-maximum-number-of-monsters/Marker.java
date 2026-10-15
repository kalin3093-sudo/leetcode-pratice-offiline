import java.util.*;
class Marker {
  public int eliminateMaximum(int[] dist, int[] speed) {
    final int n = dist.length;
    int[] arrivalTime = new int[n];

    for (int i = 0; i < n; ++i)
      arrivalTime[i] = (dist[i] - 1) / speed[i];

    Arrays.sort(arrivalTime);

    for (int i = 0; i < n; ++i)
      if (i > arrivalTime[i])
        return i;

    return n;
  }

    public boolean isCorrect(int[] dist, int[] speed, int output) {
        int expected = eliminateMaximum(dist, speed);
        return expected == output;
    }
}
