import java.util.*;
class Marker {
  public int fillCups(int[] amount) {
    final int mx = Arrays.stream(amount).max().getAsInt();
    final int sum = Arrays.stream(amount).sum();
    return Math.max(mx, (sum + 1) / 2);
  }

    public boolean isCorrect(int[] amount, int output) {
        int expected = fillCups(amount);
        return expected == output;
    }
}
