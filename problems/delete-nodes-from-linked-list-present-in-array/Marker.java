import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public ListNode modifiedList(int[] nums, ListNode head) {
    ListNode dummy = new ListNode(0, head);
    Set<Integer> numsSet = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    for (ListNode curr = dummy; curr.next != null;)
      if (numsSet.contains(curr.next.val))
        curr.next = curr.next.next;
      else
        curr = curr.next;

    return dummy.next;
  }

    public boolean isCorrect(int[] nums, ListNode head, ListNode output) {
        ListNode expected = modifiedList(nums, head);
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
