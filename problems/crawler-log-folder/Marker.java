import java.util.*;
class Marker {
  public int minOperations(String[] logs) {
    int ans = 0;

    for (final String log : logs) {
      if (log.equals("./"))
        continue;
      if (log.equals("../"))
        ans = Math.max(0, ans - 1);
      else
        ++ans;
    }

    return ans;
  }

    public boolean isCorrect(String[] logs, int output) {
        int expected = minOperations(logs);
        return expected == output;
    }
}
