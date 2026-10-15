import java.util.*;
class Marker {
  public String maximumOddBinaryNumber(String s) {
    final int zeros = (int) s.chars().filter(c -> c == '0').count();
    final int ones = s.length() - zeros;
    return "1".repeat(ones - 1) + "0".repeat(zeros) + "1";
  }

    public boolean isCorrect(String s, String output) {
        String expected = maximumOddBinaryNumber(s);
        return expected.equals(output);
    }
}
