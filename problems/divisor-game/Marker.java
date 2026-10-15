import java.util.*;
class Marker {
  public boolean divisorGame(int n) {
    return n % 2 == 0;
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = divisorGame(n);
        return expected == output;
    }
}
