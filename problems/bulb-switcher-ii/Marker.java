import java.util.*;
class Marker {
  public int flipLights(int n, int m) {
    n = Math.min(n, 3);

    if (m == 0)
      return 1;
    if (m == 1)
      return new int[] {2, 3, 4}[n - 1];
    if (m == 2)
      return new int[] {2, 4, 7}[n - 1];

    return (int) Math.pow(2, n);
  }

    public boolean isCorrect(int n, int presses, int output) {
        int expected = flipLights(n, presses);
        return expected == output;
    }
}
