import java.util.*;
class Marker {
  public boolean rotateString(String s, String goal) {
    return s.length() == goal.length() && (s + s).contains(goal);
  }

    public boolean isCorrect(String s, String goal, boolean output) {
        boolean expected = rotateString(s, goal);
        return expected == output;
    }
}
