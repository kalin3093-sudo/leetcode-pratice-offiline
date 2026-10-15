import java.util.*;
class Marker {
  public int maxIceCream(int[] costs, int coins) {
    Arrays.sort(costs);

    for (int i = 0; i < costs.length; ++i)
      if (coins >= costs[i])
        coins -= costs[i];
      else
        return i;

    return costs.length;
  }

    public boolean isCorrect(int[] costs, int coins, int output) {
        int expected = maxIceCream(costs, coins);
        return expected == output;
    }
}
