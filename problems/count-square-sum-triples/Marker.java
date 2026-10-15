import java.util.*;
class Marker {
  public int countTriples(int n) {
    int ans = 0;
    Set<Integer> squared = new HashSet<>();

    for (int i = 1; i <= n; ++i)
      squared.add(i * i);

    for (final int a : squared)
      for (final int b : squared)
        if (squared.contains(a + b))
          ++ans;

    return ans;
  }

    public boolean isCorrect(int n, int output) {
        int expected = countTriples(n);
        return expected == output;
    }
}
