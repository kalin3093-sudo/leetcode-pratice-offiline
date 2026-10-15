import java.util.*;
class Marker {
  public int[] constructRectangle(int area) {
    int width = (int) Math.sqrt(area);

    while (area % width > 0)
      --width;

    return new int[] {area / width, width};
  }

    public boolean isCorrect(int area, int[] output) {
        int[] expected = constructRectangle(area);
        return Arrays.equals(expected, output);
    }
}
