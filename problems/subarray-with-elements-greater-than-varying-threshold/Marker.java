import java.util.*;
class Marker {
  // Similar to 907. Sum of Subarray Minimums
  public int validSubarraySize(int[] nums, int threshold) {
    final int n = nums.length;
    long ans = 0;
    // prev[i] := the index k s.t. nums[k] is the previous minimum in nums[0..n)
    int[] prev = new int[n];
    // next[i] := the index k s.t. nums[k] is the next minimum in nums[i + 1..n)
    int[] next = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();

    Arrays.fill(prev, -1);
    Arrays.fill(next, n);

    for (int i = 0; i < nums.length; ++i) {
      while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        final int index = stack.pop();
        next[index] = i;
      }
      if (!stack.isEmpty())
        prev[i] = stack.peek();
      stack.push(i);
    }

    for (int i = 0; i < n; ++i) {
      // the number of `nums` in subarray containing nums[i] >= nums[i]
      final int k = (i - prev[i]) + (next[i] - i) - 1;
      if (nums[i] > threshold / (double) k)
        return k;
    }

    return -1;
  }

    public boolean isCorrect(int[] nums, int threshold, int output) {
        if (nums == null) return false;
        if (output == -1)
            return validSubarraySize(nums, threshold) == -1;
        if (output < 1 || output > nums.length) return false;
        int k = output;
        for (int i = 0; i + k <= nums.length; i++) {
            boolean ok = true;
            for (int j = i; j < i + k; j++)
                if ((long) nums[j] * k <= threshold) {
                    ok = false;
                    break;
                }
            if (ok) return true;
        }
        return false;
    }
}
