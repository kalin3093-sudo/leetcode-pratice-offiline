import java.util.*;
class Marker {
  public boolean isFascinating(int n) {
    String s = Integer.toString(n) + Integer.toString(2 * n) + Integer.toString(3 * n);
    char[] charArray = s.toCharArray();
    Arrays.sort(charArray);
    return new String(charArray).equals("123456789");
  }

    public boolean isCorrect(int n, boolean output) {
        boolean expected = isFascinating(n);
        return expected == output;
    }
}
