import java.util.*;
class Marker {
  public int bestClosingTime(String customers) {
    // Instead of computing the minimum penalty, we can compute the maximum profit.
    int ans = 0;
    int profit = 0;
    int maxProfit = 0;

    for (int i = 0; i < customers.length(); ++i) {
      profit += customers.charAt(i) == 'Y' ? 1 : -1;
      if (profit > maxProfit) {
        maxProfit = profit;
        ans = i + 1;
      }
    }

    return ans;
  }

    public boolean isCorrect(String customers, int output) {
        int expected = bestClosingTime(customers);
        return expected == output;
    }
}
