import java.util.*;
class Marker {
  public long waysToBuyPensPencils(int total, int cost1, int cost2) {
    long ans = 0;
    final int maxPen = total / cost1;

    for (int i = 0; i <= maxPen; ++i)
      ans += (total - i * cost1) / cost2 + 1;

    return ans;
  }

    public boolean isCorrect(int total, int cost1, int cost2, long output) {
        long expected = waysToBuyPensPencils(total, cost1, cost2);
        return expected == output;
    }
}
