import java.util.*;
class Marker {
  public int maxBottlesDrunk(int numBottles, int numExchange) {
    int ans = numBottles;

    while (numBottles >= numExchange) {
      numBottles = (numBottles - numExchange + 1);
      ++numExchange;
      ++ans;
    }

    return ans;
  }

    public boolean isCorrect(int numBottles, int numExchange, int output) {
        int expected = maxBottlesDrunk(numBottles, numExchange);
        return expected == output;
    }
}
