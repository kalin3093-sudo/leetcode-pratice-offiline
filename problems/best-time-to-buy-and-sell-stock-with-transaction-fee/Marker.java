import java.util.*;
class Marker {
  public int maxProfit(int[] prices, int fee) {
    int sell = 0;
    int hold = Integer.MIN_VALUE;

    for (final int price : prices) {
      sell = Math.max(sell, hold + price);
      hold = Math.max(hold, sell - price - fee);
    }

    return sell;
  }

    public boolean isCorrect(int[] prices, int fee, int output) {
        int expected = maxProfit(prices, fee);
        return expected == output;
    }
}
