import java.util.*;
class Marker {
  public int[] finalPrices(int[] prices) {
    int[] ans = prices.clone();
    Deque<Integer> stack = new ArrayDeque<>();

    for (int j = 0; j < prices.length; ++j) {
      // stack[-1] := i in the problem description.
      while (!stack.isEmpty() && prices[j] <= prices[stack.peek()])
        ans[stack.pop()] -= prices[j];
      stack.push(j);
    }

    return ans;
  }

    public boolean isCorrect(int[] prices, int[] output) {
        int[] expected = finalPrices(prices);
        return Arrays.equals(expected, output);
    }
}
