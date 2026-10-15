import java.util.*;
class Marker {
  public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;

    for (; head != null; head = head.next)
      if (head.val != val) {
        prev.next = head;
        prev = prev.next;
      }
    prev.next = null; // In case that the last value equals `val`.

    return dummy.next;
  }

    public boolean isCorrect(ListNode head, int val, ListNode output) {
        ListNode expected = removeElements(head, val);
        return sameList(expected, output);
    }

    private static boolean sameList(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }
}
