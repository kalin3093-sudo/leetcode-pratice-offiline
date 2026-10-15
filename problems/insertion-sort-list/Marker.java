import java.util.*;
class Marker {
  public ListNode insertionSortList(ListNode head) {
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy; // the last and thus largest of the sorted list

    while (head != null) {       // the current inserting node
      ListNode next = head.next; // Cache the next inserting node.
      if (prev.val >= head.val)
        prev = dummy; // Move `prev` to the front.
      while (prev.next != null && prev.next.val < head.val)
        prev = prev.next;
      head.next = prev.next;
      prev.next = head;
      head = next; // Update the current inserting node.
    }

    return dummy.next;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = insertionSortList(head);
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
