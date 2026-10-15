import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int monotoneIncreasingDigits(int n) {
    char[] s = String.valueOf(n).toCharArray();
    final int len = s.length;
    int k = len; // s[k..n) -> '9'

    for (int i = len - 1; i > 0; --i)
      if (s[i] < s[i - 1]) {
        --s[i - 1];
        k = i;
      }

    for (int i = k; i < len; ++i)
      s[i] = '9';

    return Integer.parseInt(new String(s));
  }

    public boolean isCorrect(int n, int output) {
        int expected = monotoneIncreasingDigits(n);
        return expected == output;
    }
}
