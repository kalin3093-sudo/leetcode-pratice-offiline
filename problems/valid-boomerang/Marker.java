import java.util.*;
class Marker {
  public boolean isBoomerang(int[][] points) {
    return                                                               //
        (points[1][0] - points[0][0]) * (points[2][1] - points[1][1]) != //
        (points[1][1] - points[0][1]) * (points[2][0] - points[1][0]);
  }

    public boolean isCorrect(int[][] points, boolean output) {
        boolean expected = isBoomerang(points);
        return expected == output;
    }
}
