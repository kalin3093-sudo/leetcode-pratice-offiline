import java.util.*;
class Marker {
  public int getDecimalValue(ListNode head) {
    int ans = 0;

    for (; head != null; head = head.next)
      ans = ans * 2 + head.val;

    return ans;
  }

    public boolean isCorrect(ListNode head, int output) {
        int expected = getDecimalValue(head);
        return expected == output;
    }
}
