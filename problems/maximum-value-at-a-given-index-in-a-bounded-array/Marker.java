import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public int maxValue(int n, int index, int maxSum) {
    maxSum -= n;

    int l = 0;
    int r = maxSum;

    // Find the first value x s.t. if A[index] = x, then sum(A) >= maxSum.
    while (l < r) {
      final int m = (l + r) / 2;
      if (getSum(n, index, m) >= maxSum)
        r = m;
      else
        l = m + 1;
    }

    return getSum(n, index, l) > maxSum ? l : l + 1;
  }

  // Returns the minimum sum if nums[index] = x.
  private long getSum(int n, int index, int x) {
    long l = Math.min(index, x - 1);
    long r = Math.min(n - index, x);
    long lSum = ((x - 1) + (x - 1 - l + 1)) * l / 2;
    long rSum = (x + (x - r + 1)) * r / 2;
    return lSum + rSum;
  }

    public boolean isCorrect(int n, int index, int maxSum, int output) {
        int expected = maxValue(n, index, maxSum);
        return expected == output;
    }
}
