import java.util.*;
class Marker {
  public int minimizedStringLength(String s) {
    return new HashSet<>(Arrays.asList(s.split(""))).size();
  }

    public boolean isCorrect(String s, int output) {
        int expected = minimizedStringLength(s);
        return expected == output;
    }
}
