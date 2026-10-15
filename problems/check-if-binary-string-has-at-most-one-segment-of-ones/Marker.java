import java.util.*;
class Marker {
  public boolean checkOnesSegment(String s) {
    return !s.contains("01");
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = checkOnesSegment(s);
        return expected == output;
    }
}
