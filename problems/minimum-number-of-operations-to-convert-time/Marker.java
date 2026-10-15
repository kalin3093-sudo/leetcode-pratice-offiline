import java.util.*;
class Marker {
  public int convertTime(String current, String correct) {
    final int[] ops = {60, 15, 5, 1};
    int diff = getMinutes(correct) - getMinutes(current);
    int ans = 0;

    for (final int op : ops) {
      ans += diff / op;
      diff %= op;
    }

    return ans;
  }

  private int getMinutes(final String s) {
    return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
  }

    public boolean isCorrect(String current, String correct, int output) {
        int expected = convertTime(current, correct);
        return expected == output;
    }
}
