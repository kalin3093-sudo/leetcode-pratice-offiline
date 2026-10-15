import java.util.*;
class Marker {
  public int maxWidthRamp(int[] nums) {
    int ans = 0;
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < nums.length; ++i)
      if (stack.isEmpty() || nums[i] < nums[stack.peek()])
        stack.push(i);

    for (int i = nums.length - 1; i > ans; --i)
      while (!stack.isEmpty() && nums[i] >= nums[stack.peek()])
        ans = Math.max(ans, i - stack.pop());

    return ans;
  }

    public boolean isCorrect(int[] nums, int output) {
        int expected = maxWidthRamp(nums);
        return expected == output;
    }
}
