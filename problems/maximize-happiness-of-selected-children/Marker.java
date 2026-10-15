import java.util.*;
class Marker {
  public long maximumHappinessSum(int[] happiness, int k) {
    final int n = happiness.length;
    long ans = 0;
    int decremented = 0;

    Arrays.sort(happiness);

    for (int i = n - 1; i >= n - k; --i) {
      ans += Math.max(0, happiness[i] - decremented);
      ++decremented;
    }

    return ans;
  }

    public boolean isCorrect(int[] happiness, int k, long output) {
        long expected = maximumHappinessSum(happiness, k);
        return expected == output;
    }
}
