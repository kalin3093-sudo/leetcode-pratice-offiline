import java.util.*;
class Marker {
  public int strStr(String haystack, String needle) {
    final int m = haystack.length();
    final int n = needle.length();

    for (int i = 0; i < m - n + 1; ++i)
      if (haystack.substring(i, i + n).equals(needle))
        return i;

    return -1;
  }

    public boolean isCorrect(String haystack, String needle, int output) {
        int expected = strStr(haystack, needle);
        return expected == output;
    }
}
