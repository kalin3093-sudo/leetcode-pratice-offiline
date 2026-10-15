import java.util.*;
class Marker {
  public ListNode removeZeroSumSublists(ListNode head) {
    ListNode dummy = new ListNode(0, head);
    int prefix = 0;
    Map<Integer, ListNode> prefixToNode = new HashMap<>();
    prefixToNode.put(0, dummy);

    for (; head != null; head = head.next) {
      prefix += head.val;
      prefixToNode.put(prefix, head);
    }

    prefix = 0;

    for (head = dummy; head != null; head = head.next) {
      prefix += head.val;
      head.next = prefixToNode.get(prefix).next;
    }

    return dummy.next;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = removeZeroSumSublists(head);
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
