import java.util.*;
class Marker {
  public ListNode doubleIt(ListNode head) {
    if (getCarry(head) == 1)
      return new ListNode(1, head);
    return head;
  }

  private int getCarry(ListNode node) {
    int val = node.val * 2;
    if (node.next != null)
      val += getCarry(node.next);
    node.val = val % 10;
    return val / 10;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = doubleIt(head);
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
