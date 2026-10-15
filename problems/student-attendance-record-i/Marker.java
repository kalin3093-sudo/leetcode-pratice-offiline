import java.util.*;
class Marker {
  public boolean checkRecord(String s) {
    return s.indexOf("A") == s.lastIndexOf("A") && !s.contains("LLL");
  }

    public boolean isCorrect(String s, boolean output) {
        boolean expected = checkRecord(s);
        return expected == output;
    }
}
