import java.util.*;
class Marker {
  public String convertToBase7(int num) {
    if (num < 0)
      return "-" + convertToBase7(-num);
    if (num < 7)
      return String.valueOf(num);
    return convertToBase7(num / 7) + String.valueOf(num % 7);
  }

    public boolean isCorrect(int num, String output) {
        String expected = convertToBase7(num);
        return expected.equals(output);
    }
}
