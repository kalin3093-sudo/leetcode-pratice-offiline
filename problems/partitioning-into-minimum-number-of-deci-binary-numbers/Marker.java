import java.util.*;
class Marker {
  public int minPartitions(String n) {
    return Character.getNumericValue(n.chars().max().getAsInt());
  }

    public boolean isCorrect(String n, int output) {
        int expected = minPartitions(n);
        return expected == output;
    }
}
