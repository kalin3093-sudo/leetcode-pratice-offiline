import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public long smallestNumber(long num) {
    String[] digits = String.valueOf(Math.abs(num)).split("");
    String s = Stream.of(digits).sorted().collect(Collectors.joining());
    StringBuilder sb = new StringBuilder(s);
    if (num <= 0)
      return -1 * Long.parseLong(sb.reverse().toString());
    if (sb.charAt(0) == '0') {
      final int firstNonZeroIndex = sb.lastIndexOf("0") + 1;
      sb.setCharAt(0, sb.charAt(firstNonZeroIndex));
      sb.setCharAt(firstNonZeroIndex, '0');
    }
    return Long.parseLong(sb.toString());
  }

    public boolean isCorrect(long num, long output) {
        long expected = smallestNumber(num);
        return expected == output;
    }
}
