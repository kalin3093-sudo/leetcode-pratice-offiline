import java.util.*;
class Marker {
  public int flipgame(int[] fronts, int[] backs) {
    final int MAX = 2001;
    int ans = MAX;
    Set<Integer> same = new HashSet<>();

    for (int i = 0; i < fronts.length; ++i)
      if (fronts[i] == backs[i])
        same.add(fronts[i]);

    for (final int front : fronts)
      if (!same.contains(front))
        ans = Math.min(ans, front);

    for (final int back : backs)
      if (!same.contains(back))
        ans = Math.min(ans, back);

    return ans == MAX ? 0 : ans;
  }

    public boolean isCorrect(int[] fronts, int[] backs, int output) {
        int expected = flipgame(fronts, backs);
        return expected == output;
    }
}
