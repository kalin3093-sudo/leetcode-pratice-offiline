import java.util.*;
class Marker {
  public int titleToNumber(String columnTitle) {
    return columnTitle.chars().reduce(0, (subtotal, c) -> subtotal * 26 + c - '@');
  }

    public boolean isCorrect(String columnTitle, int output) {
        int expected = titleToNumber(columnTitle);
        return expected == output;
    }
}
