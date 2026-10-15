import java.util.*;
class Marker {
  public int findLUSlength(String a, String b) {
    return a.equals(b) ? -1 : Math.max(a.length(), b.length());
  }

    public boolean isCorrect(String a, String b, int output) {
        int expected = findLUSlength(a, b);
        return expected == output;
    }
}
