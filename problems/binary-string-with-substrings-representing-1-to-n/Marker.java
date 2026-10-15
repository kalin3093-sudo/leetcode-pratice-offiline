import java.util.*;
class Marker {
  public boolean queryString(String s, int n) {
    if (n > 1511)
      return false;

    for (int i = n; i > n / 2; --i)
      if (!s.contains(Integer.toBinaryString(i)))
        return false;

    return true;
  }

    public boolean isCorrect(String s, int n, boolean output) {
        boolean expected = queryString(s, n);
        return expected == output;
    }
}
