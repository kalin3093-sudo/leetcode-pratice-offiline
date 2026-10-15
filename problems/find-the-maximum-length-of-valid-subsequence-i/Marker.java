import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maximumLength(int[] nums) {
    // dp[i][j] := the maximum length of a valid subsequence, where the last
    // number mod 2 equal to i and the next desired number mod 2 equal to j
    int[][] dp = new int[2][2];

    // Extend the pattern xyxyxy...xy.
    for (final int x : nums)
      for (int y = 0; y < 2; ++y)
        dp[x % 2][y] = dp[y][x % 2] + 1;

    return Arrays.stream(dp).flatMapToInt(Arrays::stream).max().getAsInt();
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maximumLength(nums);
        return expected == output;
    }
}
