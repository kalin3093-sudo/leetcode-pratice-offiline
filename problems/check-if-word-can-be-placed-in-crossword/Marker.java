import java.util.*;
class Marker {
  public boolean placeWordInCrossword(char[][] board, String word) {
    // ponytail: harness to_char_array maps blank cells to NUL, statement uses ' '
    for (char[] row : board)
      for (int i = 0; i < row.length; ++i)
        if (row[i] == '\0')
          row[i] = ' ';
    for (char[][] state : new char[][][] {board, getRotated(board)})
      for (char[] chars : state)
        for (final String token : String.valueOf(chars).split("#"))
          for (final String letters :
               new String[] {word, new StringBuilder(word).reverse().toString()})
            if (letters.length() == token.length())
              if (canFit(letters, token))
                return true;
    return false;
  }

  private char[][] getRotated(char[][] board) {
    final int m = board.length;
    final int n = board[0].length;
    char[][] rotated = new char[n][m];
    for (int i = 0; i < m; ++i)
      for (int j = 0; j < n; ++j)
        rotated[j][i] = board[i][j];
    return rotated;
  }

  private boolean canFit(final String letters, final String token) {
    for (int i = 0; i < letters.length(); ++i)
      if (token.charAt(i) != ' ' && token.charAt(i) != letters.charAt(i))
        return false;
    return true;
  }

    public boolean isCorrect(char[][] board, String word, boolean output) {
        boolean expected = placeWordInCrossword(board, word);
        return expected == output;
    }
}
