import java.util.*;
class Marker {
  public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
    long cost = 0;
    // Stores `chargeTimes[i]`.
    Deque<Integer> maxQ = new ArrayDeque<>();

    int j = 0; // window's range := [i..j], so k = i - j + 1
    for (int i = 0; i < chargeTimes.length; ++i) {
      cost += runningCosts[i];
      while (!maxQ.isEmpty() && maxQ.peekLast() < chargeTimes[i])
        maxQ.pollLast();
      maxQ.offerLast(chargeTimes[i]);
      if (maxQ.peekFirst() + (i - j + 1) * cost > budget) {
        if (maxQ.peekFirst() == chargeTimes[j])
          maxQ.pollFirst();
        cost -= runningCosts[j++];
      }
    }

    return chargeTimes.length - j;
  }

    public boolean isCorrect(int[] chargeTimes, int[] runningCosts, long budget, int output) {
        int expected = maximumRobots(chargeTimes, runningCosts, budget);
        return expected == output;
    }
}
