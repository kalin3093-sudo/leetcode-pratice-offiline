import java.util.*;
class Marker {
  public int repeatedStringMatch(String A, String B) {
    final int n = (int) Math.ceil((double) B.length() / (double) A.length());
    final String s = String.join("", Collections.nCopies(n, A));
    if (s.contains(B))
      return n;
    if ((s + A).contains(B))
      return n + 1;
    return -1;
  }

    public boolean isCorrect(String a, String b, int output) {
        int expected = repeatedStringMatch(a, b);
        return expected == output;
    }
}
