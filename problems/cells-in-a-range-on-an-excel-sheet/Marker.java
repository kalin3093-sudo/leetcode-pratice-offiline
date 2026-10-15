import java.util.*;
class Marker {
  public List<String> cellsInRange(String s) {
    List<String> ans = new ArrayList<>();
    final char startCol = s.charAt(0);
    final char endCol = s.charAt(3);
    final char startRow = s.charAt(1);
    final char endRow = s.charAt(4);

    for (char col = startCol; col <= endCol; ++col)
      for (char row = startRow; row <= endRow; ++row)
        ans.add("" + col + row);

    return ans;
  }

    public boolean isCorrect(String s, List<String> output) {
        List<String> expected = cellsInRange(s);
        return expected.equals(output);
    }
}
