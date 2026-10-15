import java.util.*;
class Marker {
  public boolean judgeCircle(String moves) {
    int right = 0;
    int up = 0;

    for (final char move : moves.toCharArray()) {
      switch (move) {
        case 'R':
          ++right;
          break;
        case 'L':
          --right;
          break;
        case 'U':
          ++up;
          break;
        case 'D':
          --up;
          break;
      }
    }

    return right == 0 && up == 0;
  }

    public boolean isCorrect(String moves, boolean output) {
        boolean expected = judgeCircle(moves);
        return expected == output;
    }
}
