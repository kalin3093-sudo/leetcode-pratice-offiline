import java.util.*;
class Marker {
  public boolean checkString(String s) {
    return !s.contains("ba");
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = checkString(s);
        return expected == output;
    }
}
