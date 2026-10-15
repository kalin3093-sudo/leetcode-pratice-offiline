import java.util.*;
class Marker {
  public int shortestSubarray(int[] nums, int k) {
    final int n = nums.length;
    int ans = n + 1;
    Deque<Integer> dq = new ArrayDeque<>();
    long[] prefix = new long[n + 1];

    for (int i = 0; i < n; ++i)
      prefix[i + 1] = (long) nums[i] + prefix[i];

    for (int i = 0; i < n + 1; ++i) {
      while (!dq.isEmpty() && prefix[i] - prefix[dq.getFirst()] >= k)
        ans = Math.min(ans, i - dq.pollFirst());
      while (!dq.isEmpty() && prefix[i] <= prefix[dq.getLast()])
        dq.pollLast();
      dq.offerLast(i);
    }

    return ans <= n ? ans : -1;
  }

    public boolean isCorrect(int[] nums, int k, int output) {
        int expected = shortestSubarray(nums, k);
        return expected == output;
    }
}
