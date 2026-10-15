import java.util.*;
class Marker {
  public int numComponents(ListNode head, int[] nums) {
    int ans = 0;
    Set<Integer> setNums = new HashSet<>();

    for (final int g : nums)
      setNums.add(g);

    for (; head != null; head = head.next)
      if (setNums.contains(head.val) && (head.next == null || !setNums.contains(head.next.val)))
        ++ans;

    return ans;
  }

    public boolean isCorrect(ListNode head, int[] nums, int output) {
        int expected = numComponents(head, nums);
        return expected == output;
    }
}
