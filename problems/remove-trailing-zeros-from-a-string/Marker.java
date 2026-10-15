import java.util.*;
class Marker {
  public String removeTrailingZeros(String num) {
    return num.replaceAll("0+$", "");
  }

    public boolean isCorrect(String num, String output) {
        String expected = removeTrailingZeros(num);
        return expected.equals(output);
    }
}
