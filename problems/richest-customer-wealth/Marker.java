import java.util.*;
class Marker {
  public int maximumWealth(int[][] accounts) {
    return Arrays.stream(accounts)
        .mapToInt(account -> Arrays.stream(account).sum())
        .max()
        .getAsInt();
  }

    public boolean isCorrect(int[][] accounts, int output) {
        int expected = maximumWealth(accounts);
        return expected == output;
    }
}
