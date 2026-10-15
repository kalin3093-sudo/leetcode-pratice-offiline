import java.util.*;
class Marker {
  public int hIndex(int[] citations) {
    final int n = citations.length;
    int l = 0;
    int r = n;

    while (l < r) {
      final int m = (l + r) / 2;
      if (citations[m] + m >= n)
        r = m;
      else
        l = m + 1;
    }

    return n - l;
  }

    public boolean isCorrect(int[] citations, int output) {
        int expected = hIndex(citations);
        return expected == output;
    }
}
