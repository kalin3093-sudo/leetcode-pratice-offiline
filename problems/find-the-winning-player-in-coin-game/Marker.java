import java.util.*;
class Marker {
  public String winningPlayer(int x, int y) {
    return Math.min(x, y / 4) % 2 == 0 ? "Bob" : "Alice";
  }

    public boolean isCorrect(int x, int y, String output) {
        String expected = winningPlayer(x, y);
        return expected.equals(output);
    }
}
