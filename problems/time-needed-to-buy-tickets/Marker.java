import java.util.*;
class Marker {
  public int timeRequiredToBuy(int[] tickets, int k) {
    int ans = 0;

    for (int i = 0; i < tickets.length; ++i)
      if (i <= k)
        ans += Math.min(tickets[i], tickets[k]);
      else
        ans += Math.min(tickets[i], tickets[k] - 1);

    return ans;
  }

    public boolean isCorrect(int[] tickets, int k, int output) {
        int expected = timeRequiredToBuy(tickets, k);
        return expected == output;
    }
}
