import java.util.*;
class Marker {
  public int finalValueAfterOperations(String[] operations) {
    int ans = 0;

    for (final String op : operations)
      ans += op.charAt(1) == '+' ? 1 : -1;

    return ans;
  }

    public boolean isCorrect(String[] operations, int output) {
        int expected = finalValueAfterOperations(operations);
        return expected == output;
    }
}
