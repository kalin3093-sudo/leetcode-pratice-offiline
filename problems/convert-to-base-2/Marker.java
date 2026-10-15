import java.util.*;
class Marker {
  public String baseNeg2(int n) {
    StringBuilder sb = new StringBuilder();

    while (n != 0) {
      sb.append(n & 1);
      n = -(n >> 1);
    }

    return sb.length() > 0 ? sb.reverse().toString() : "0";
  }

    public boolean isCorrect(int n, String output) {
        String expected = baseNeg2(n);
        return expected.equals(output);
    }
}
