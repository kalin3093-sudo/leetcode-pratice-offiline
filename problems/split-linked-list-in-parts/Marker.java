import java.util.*;
class Marker {
    public ListNode[] splitListToParts(ListNode head, int k) {
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) len++;
        ListNode[] res = new ListNode[k];
        int base = len / k, extra = len % k;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            int size = base + (i < extra ? 1 : 0);
            if (size == 0) {
                res[i] = null;
                continue;
            }
            res[i] = cur;
            for (int j = 1; j < size; j++) cur = cur.next;
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return res;
    }
    private boolean equalsList(ListNode a, ListNode b) {
        while (a != null && b != null) {
            if (a.val != b.val) return false;
            a = a.next;
            b = b.next;
        }
        return a == null && b == null;
    }
    public boolean isCorrect(ListNode head, int k, ListNode[] output) {
        if (output == null || output.length != k) return false;
        ListNode[] ref = splitListToParts(head, k);
        for (int i = 0; i < k; i++) {
            if (!equalsList(ref[i], output[i])) return false;
        }
        return true;
    }
}
