import java.util.*;
class Marker {
  public boolean makeStringsEqual(String s, String target) {
    return s.contains("1") == target.contains("1");
  }

    public boolean isCorrect(String s, String target, boolean output) {
        boolean expected = makeStringsEqual(s, target);
        return expected == output;
    }
}
