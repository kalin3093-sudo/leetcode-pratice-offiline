import java.util.*;
class Marker {
  public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
    return squareIsWhite(coordinate1) == squareIsWhite(coordinate2);
  }

  // Same as 1812. Determine Color of a Chessboard Square
  private boolean squareIsWhite(final String coordinates) {
    final char letter = coordinates.charAt(0);
    final char digit = coordinates.charAt(1);
    return letter % 2 != digit % 2;
  }

    public boolean isCorrect(String coordinate1, String coordinate2, boolean output) {
        boolean expected = checkTwoChessboards(coordinate1, coordinate2);
        return expected == output;
    }
}
