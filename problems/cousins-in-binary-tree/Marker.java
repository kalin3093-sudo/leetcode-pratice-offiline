import java.util.*;
class Marker {
    public boolean isCousins(TreeNode root, int x, int y) {
        Map<Integer, Integer> depth = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        depth.put(root.val, 0);
        parent.put(root.val, -1);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                depth.put(cur.left.val, depth.get(cur.val) + 1);
                parent.put(cur.left.val, cur.val);
                q.add(cur.left);
            }
            if (cur.right != null) {
                depth.put(cur.right.val, depth.get(cur.val) + 1);
                parent.put(cur.right.val, cur.val);
                q.add(cur.right);
            }
        }
        return depth.get(x).equals(depth.get(y)) && !parent.get(x).equals(parent.get(y));
    }

    public boolean isCorrect(TreeNode root, int x, int y, boolean output) {
        return isCousins(root, x, y) == output;
    }
}
