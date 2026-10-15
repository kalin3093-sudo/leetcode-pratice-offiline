import java.util.*;
class Marker {
  public int minTimeToVisitAllPoints(int[][] points) {
    int ans = 0;

    for (int i = 1; i < points.length; ++i)
      ans += Math.max(Math.abs(points[i][0] - points[i - 1][0]),
                      Math.abs(points[i][1] - points[i - 1][1]));

    return ans;
  }

    public boolean isCorrect(int[][] points, int output) {
        int expected = minTimeToVisitAllPoints(points);
        return expected == output;
    }
}
