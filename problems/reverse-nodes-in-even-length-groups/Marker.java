import java.util.*;
import java.util.stream.*;
import java.util.function.*;
class Marker {
  public ListNode reverseEvenLengthGroups(ListNode head) {
    // prev -> (head -> ... -> tail) -> next -> ...
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;
    ListNode tail = head;
    ListNode next = head.next;
    int groupLength = 1;

    while (true) {
      if (groupLength % 2 == 1) {
        prev.next = head;
        prev = tail;
      } else {
        tail.next = null;
        prev.next = reverse(head);
        // Prev -> (tail -> ... -> head) -> next -> ...
        head.next = next;
        prev = head;
      }
      if (next == null)
        break;
      head = next;
      Pair<ListNode, Integer> res = getTailAndLength(head, groupLength + 1);
      tail = res.getKey();
      next = tail.next;
      groupLength = res.getValue();
    }

    return dummy.next;
  }

  private Pair<ListNode, Integer> getTailAndLength(ListNode head, int groupLength) {
    int length = 1;
    ListNode tail = head;
    while (length < groupLength && tail.next != null) {
      tail = tail.next;
      ++length;
    }
    return new Pair<>(tail, length);
  }

  ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
      ListNode next = head.next;
      head.next = prev;
      prev = head;
      head = next;
    }
    return prev;
  }

    public boolean isCorrect(ListNode head, ListNode output) {
        ListNode expected = reverseEvenLengthGroups(head);
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
class Pair<K, V> {
    private final K key;
    private final V value;
    Pair(K key, V value) { this.key = key; this.value = value; }
    public K getKey() { return key; }
    public V getValue() { return value; }
}
