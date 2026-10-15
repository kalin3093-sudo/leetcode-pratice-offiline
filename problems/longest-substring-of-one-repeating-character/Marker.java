import java.util.*;
class Marker {
    static class Node {
        int maxLen;
        int leftLen;
        int rightLen;
        char leftChar;
        char rightChar;
        int len;
    }
    
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int q = queryCharacters.length();
        Node[] tree = new Node[4 * n];
        char[] arr = s.toCharArray();
        
        build(tree, arr, 1, 0, n - 1);
        
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            arr[idx] = c;
            update(tree, 1, 0, n - 1, idx, c);
            ans[i] = tree[1].maxLen;
        }
        return ans;
    }
    
    private void build(Node[] tree, char[] arr, int node, int l, int r) {
        tree[node] = new Node();
        if (l == r) {
            tree[node].maxLen = tree[node].leftLen = tree[node].rightLen = 1;
            tree[node].leftChar = tree[node].rightChar = arr[l];
            tree[node].len = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(tree, arr, node << 1, l, mid);
        build(tree, arr, node << 1 | 1, mid + 1, r);
        pull(tree, node);
    }
    
    private void update(Node[] tree, int node, int l, int r, int idx, char c) {
        if (l == r) {
            tree[node].maxLen = tree[node].leftLen = tree[node].rightLen = 1;
            tree[node].leftChar = tree[node].rightChar = c;
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) update(tree, node << 1, l, mid, idx, c);
        else update(tree, node << 1 | 1, mid + 1, r, idx, c);
        pull(tree, node);
    }
    
    private void pull(Node[] tree, int node) {
        Node left = tree[node << 1];
        Node right = tree[node << 1 | 1];
        Node cur = tree[node];
        
        cur.len = left.len + right.len;
        cur.leftChar = left.leftChar;
        cur.rightChar = right.rightChar;
        
        cur.leftLen = left.leftLen;
        if (left.leftLen == left.len && left.rightChar == right.leftChar) {
            cur.leftLen = left.len + right.leftLen;
        }
        
        cur.rightLen = right.rightLen;
        if (right.rightLen == right.len && right.leftChar == left.rightChar) {
            cur.rightLen = right.len + left.rightLen;
        }
        
        cur.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.rightChar == right.leftChar) {
            cur.maxLen = Math.max(cur.maxLen, left.rightLen + right.leftLen);
        }
    }

    public boolean isCorrect(String s, String queryCharacters, int[] queryIndices, int[] output) {
        int[] res = longestRepeating(s, queryCharacters, queryIndices);
        return Arrays.equals(res, output);
    }
}