import java.util.*;

class Marker {
  public String reversePrefix(String s, int k) {
    char[] a = s.toCharArray();
    for (int i = 0, j = k - 1; i < j; i++, j--) {
      char t = a[i];
      a[i] = a[j];
      a[j] = t;
    }
    return new String(a);
  }

  public boolean isCorrect(String s, int k, String output) {
    return output != null && output.equals(reversePrefix(s, k));
  }
}
