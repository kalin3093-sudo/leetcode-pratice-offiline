import java.util.*;
class Marker {
  public int countSeniors(String[] details) {
    return (int) Arrays.stream(details)
        .filter(detail -> Integer.parseInt(detail.substring(11, 13)) > 60)
        .count();
  }

    public boolean isCorrect(String[] details, int output) {
        int expected = countSeniors(details);
        return expected == output;
    }
}
