import java.util.*;
class Marker {
  public ListNode deleteDuplicates(ListNode head) {
    ListNode curr = head;

    while (curr != null) {
      while (curr.next != null && curr.val == curr.next.val)
        curr.next = curr.next.next;
      curr = curr.next;
    }

    return head;
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
