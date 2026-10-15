import java.util.*;
class Marker {
  public boolean squareIsWhite(String coordinates) {
    final char letter = coordinates.charAt(0);
    final char digit = coordinates.charAt(1);
    return letter % 2 != digit % 2;
  }

    public boolean isCorrect(String coordinates, boolean output) {
        boolean expected = squareIsWhite(coordinates);
        return expected == output;
    }
}
