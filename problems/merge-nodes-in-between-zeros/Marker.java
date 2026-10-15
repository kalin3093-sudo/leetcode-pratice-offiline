import java.util.*;
class Marker {
  public ListNode mergeNodes(ListNode head) {
    if (head == null)
      return null;
    if (head.next.val == 0) {
      ListNode node = new ListNode(head.val);
      node.next = mergeNodes(head.next.next);
      return node;
    }

    ListNode next = mergeNodes(head.next);
    next.val += head.val;
    return next;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = mergeNodes(head);
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
