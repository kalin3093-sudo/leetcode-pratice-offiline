import java.util.*;
class Marker {
  public boolean haveConflict(String[] event1, String[] event2) {
    return event1[0].compareTo(event2[1]) <= 0 && event2[0].compareTo(event1[1]) <= 0;
  }

    public boolean isCorrect(String[] event1, String[] event2, boolean output) {
        boolean expected = haveConflict(event1, event2);
        return expected == output;
    }
}
