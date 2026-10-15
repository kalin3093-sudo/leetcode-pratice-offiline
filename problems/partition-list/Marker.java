import java.util.*;
class Marker {
  public ListNode partition(ListNode head, int x) {
    ListNode beforeHead = new ListNode(0);
    ListNode afterHead = new ListNode(0);
    ListNode before = beforeHead;
    ListNode after = afterHead;

    for (; head != null; head = head.next)
      if (head.val < x) {
        before.next = head;
        before = head;
      } else {
        after.next = head;
        after = head;
      }

    after.next = null;
    before.next = afterHead.next;

    return beforeHead.next;
  }

    public boolean isCorrect(ListNode head, int x, ListNode output) {
        ListNode expected = partition(head, x);
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
