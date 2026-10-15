import java.util.*;
class Marker {
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;

    while (head != null) {
      while (head.next != null && head.val == head.next.val)
        head = head.next;
      if (prev.next == head)
        prev = prev.next;
      else
        prev.next = head.next;
      head = head.next;
    }

    return dummy.next;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = deleteDuplicates(head);
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
