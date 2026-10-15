import java.util.*;
class Marker {
  public ListNode swapNodes(ListNode head, int k) {
    ListNode p = null; // Points the k-th node from the beginning.
    ListNode q = null; // Points the k-th node from the end.

    for (ListNode curr = head; curr != null; curr = curr.next) {
      if (q != null)
        q = q.next;
      if (--k == 0) {
        p = curr;
        q = head;
      }
    }

    final int temp = p.val;
    p.val = q.val;
    q.val = temp;
    return head;
  }

    public boolean isCorrect(ListNode head, int k, ListNode output) {
        ListNode expected = swapNodes(head, k);
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
