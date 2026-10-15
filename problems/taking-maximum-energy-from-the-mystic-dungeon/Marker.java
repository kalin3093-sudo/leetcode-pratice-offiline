import java.util.*;
class Marker {
  public int maximumEnergy(int[] energy, int k) {
    int[] dp = energy.clone();
    for (int i = energy.length - 1 - k; i >= 0; --i)
      dp[i] += dp[i + k];
    return Arrays.stream(dp).max().getAsInt();
  }

    public boolean isCorrect(int[] energy, int k, int output) {
        int expected = maximumEnergy(energy, k);
        return expected == output;
    }
}
