import java.util.*;
class Marker {
  public ListNode oddEvenList(ListNode head) {
    ListNode oddHead = new ListNode(0);
    ListNode evenHead = new ListNode(0);
    ListNode odd = oddHead;
    ListNode even = evenHead;

    for (boolean isOdd = true; head != null; head = head.next, isOdd = !isOdd)
      if (isOdd) {
        odd.next = head;
        odd = odd.next;
      } else {
        even.next = head;
        even = even.next;
      }

    odd.next = evenHead.next;
    even.next = null;
    return oddHead.next;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = oddEvenList(head);
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
