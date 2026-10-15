import java.util.*;
class Marker {
  public int numberOfRounds(String loginTime, String logoutTime) {
    final int start = getMinutes(loginTime);
    int finish = getMinutes(logoutTime);
    if (start > finish)
      finish += 60 * 24;
    return Math.max(0, finish / 15 - (start + 14) / 15);
  }

  private int getMinutes(final String s) {
    return 60 * Integer.valueOf(s.substring(0, 2)) + Integer.valueOf(s.substring(3));
  }

    public boolean isCorrect(String loginTime, String logoutTime, int output) {
        int expected = numberOfRounds(loginTime, logoutTime);
        return expected == output;
    }
}
